package com.example.voicerecorder.ui.screens.recording

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.voicerecorder.R
import com.example.voicerecorder.audiorecorder.AudioRecorderService
import com.example.voicerecorder.ui.composable.RoundButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecordingScreen(viewModel: RecordingViewModel = koinViewModel()) {

    val context = LocalContext.current
    val audioIntent = Intent(
        context,
        AudioRecorderService::class.java
    )

    val permReqLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value
            }
            if (granted) {
                context.startForegroundService(audioIntent)
            } else {
                // show dialog about turning on notifications
            }
        }

    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.RECORD_AUDIO
        )
    } else {
        arrayOf(
            Manifest.permission.RECORD_AUDIO
        )
    }

//    TODO do we need column?
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundButton(
            iconId = if (viewModel.isRecording.value) R.drawable.baseline_stop
            else R.drawable.baseline_fiber_manual_record,
            iconSize = 120.dp
        ) {
            if (!viewModel.isRecording.value) {
                if (hasPermissions(context, permissions)) {
                    viewModel.isRecording.value = true
                    context.startForegroundService(audioIntent)
                } else {
                    permReqLauncher.launch(
                        permissions
                    )
                }
            } else {
                context.stopService(audioIntent)
                viewModel.isRecording.value = false
            }
        }
    }
}

private fun hasPermissions(context: Context, permissions: Array<String>): Boolean =
    permissions.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

