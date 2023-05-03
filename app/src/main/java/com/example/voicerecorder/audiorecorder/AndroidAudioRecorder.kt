package com.example.voicerecorder.audiorecorder

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import com.example.voicerecorder.AudioRecord
import com.example.voicerecorder.repo.RecorderRepo
import com.example.voicerecorder.utils.AppConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

//TODO strings
class AndroidAudioRecorder(
    private val context: Context,
    private val repository: RecorderRepo
) : AudioRecorder {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private var recorder: MediaRecorder? = null
    private var filePath: String? = null

    //    TODO use koin?
    private fun createRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else MediaRecorder()
    }

    override fun start() {
        filePath = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            Environment.getExternalStorageDirectory()
                .toString() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + "recording_" + System.currentTimeMillis()
                .toString() + ".m4a"
        } else {
            "${context.externalCacheDir?.absolutePath}/recording_${System.currentTimeMillis()}.m4a"
        }

        createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setAudioEncodingBitRate(16 * 44100)
            setAudioSamplingRate(96000)
            setOutputFile(filePath)

            prepare()
            start()

            recorder = this
        }
    }


    override fun stop() {
        recorder?.stop()
        recorder?.reset()
        recorder = null

        val date = System.currentTimeMillis()
        val dateFormatted =
            SimpleDateFormat(AppConstants.DATE_FORMAT, Locale.getDefault()).format(Date(date))
        val record = AudioRecord(
            filename = "Audio Record $date",
            filePath = filePath,
            date = dateFormatted,
        )
        scope.launch {
            repository.insertRecording(record)
        }
//        TODO coroutine cancellation
//        job.cancel()
    }
}