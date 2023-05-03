package com.example.voicerecorder.ui.screens.playing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voicerecorder.AudioRecord
import com.example.voicerecorder.R
import com.example.voicerecorder.playback.AndroidAudioPlayer
import com.example.voicerecorder.ui.composable.RoundButton

//TODO remove bottom navigation
//TODO change drawable size
@Composable
fun PlayingScreen(audioRecord: AudioRecord) {

    val context = LocalContext.current
    var isPlaying by rememberSaveable {
        mutableStateOf(false)
    }
    val player by lazy {
        AndroidAudioPlayer(context) { isPlaying = false }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 28.dp),
            text = audioRecord.filename,
            fontSize = 26.sp
        )
        RoundButton(
            iconId = if (isPlaying) R.drawable.baseline_stop else R.drawable.baseline_play_arrow,
            iconSize = 120.dp,
            color = Color.LightGray
        ) {
            if (isPlaying) {
                player.stop()
            } else {
                player.play(audioRecord)
            }
            isPlaying = !isPlaying
        }
    }
}