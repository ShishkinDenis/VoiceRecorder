package com.example.voicerecorder.audiorecoder

import android.content.ContentValues
import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import android.provider.MediaStore

//TODO strings
class AndroidAudioRecorder(
    private val context: Context
) : AudioRecorder {

    private var recorder: MediaRecorder? = null

    private fun createRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else MediaRecorder()
    }

    override fun start() {
        val values = ContentValues()
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, "fileName")
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/mpeg")
        values.put(MediaStore.Audio.Media.RELATIVE_PATH, Environment.DIRECTORY_MUSIC)
        val audiouri =
            context.contentResolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values)
        val file = audiouri?.let { context.contentResolver.openFileDescriptor(it, "w") };


        if (file != null) {
            createRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setOutputFile(file.fileDescriptor)

                prepare()
                start()

                recorder = this
            }
        }
    }

    override fun stop() {
        recorder?.stop()
        recorder?.reset()
        recorder = null
    }
}