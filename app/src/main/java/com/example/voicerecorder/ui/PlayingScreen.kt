package com.example.voicerecorder.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.voicerecorder.playback.AndroidAudioPlayer
import org.koin.androidx.compose.koinViewModel

//TODO strings
@Composable
fun PlayingScreen(viewModel: RecordingViewModel = koinViewModel()) {

    val context = LocalContext.current
    val player by lazy {
        AndroidAudioPlayer(context)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            player.playFile()
        }) {
            Text(text = "Play")
        }
        Button(onClick = {
            player.stop()
        }) {
            Text(text = "Stop playing")
        }
    }
}