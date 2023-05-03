package com.example.voicerecorder.playback

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import com.example.voicerecorder.AudioRecord

//TODO check if player is already launched
class AndroidAudioPlayer(
    private val context: Context,
    private val onCompleted: () -> Unit
) : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun play(audioRecord: AudioRecord) {
        MediaPlayer.create(context, audioRecord.filePath?.toUri()).apply {
            player = this
            start()
        }.setOnCompletionListener {
            onCompleted()
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}