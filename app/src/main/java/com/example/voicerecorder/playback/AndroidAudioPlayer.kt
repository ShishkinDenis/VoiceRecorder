package com.example.voicerecorder.playback

import android.content.Context
import android.media.MediaPlayer
import android.os.Environment
import androidx.core.net.toUri
import java.io.File

class AndroidAudioPlayer(
    private val context: Context
) : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playFile() {
        val path: File = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_MUSIC
        )
        val file = File(path, "fileName.mp3")

        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            start()
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}