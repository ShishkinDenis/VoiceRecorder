package com.example.voicerecorder.playback

import com.example.voicerecorder.database.AudioRecord


interface AudioPlayer {
    fun play(audioRecord: AudioRecord)
    fun stop()
}