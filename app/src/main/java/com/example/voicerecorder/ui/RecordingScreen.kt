package com.example.voicerecorder.ui

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.voicerecorder.audiorecoder.AudioRecorderService
import com.example.voicerecorder.ui.theme.Purple200
import org.koin.androidx.compose.koinViewModel

//TODO strings
@Composable
fun RecordingScreen(viewModel: RecordingViewModel = koinViewModel()) {

    val context = LocalContext.current
    val audioIntent = Intent(
        context,
        AudioRecorderService::class.java
    )
    val color = if (viewModel.selected.value) Color.Yellow else Purple200

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                viewModel.doExampleFunc()
                viewModel.selected.value = true
                context.startForegroundService(audioIntent)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = color)
        ) {
            Text(text = "Start recording")
        }
        Button(onClick = {
            viewModel.selected.value = false
            context.stopService(audioIntent)
        }) {
            Text(text = "Stop recording")
        }
    }
}