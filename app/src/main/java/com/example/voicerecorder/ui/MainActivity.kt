package com.example.voicerecorder.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.example.voicerecorder.navigation.BottomNavigationBar
import com.example.voicerecorder.navigation.NavigationSetup
import com.example.voicerecorder.ui.theme.VoiceRecorderTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestAudioRecordingPermission()
        setContent {
            VoiceRecorderTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = { BottomNavigationBar(navController) }
                    ) {
                        NavigationSetup(navController = navController)
                    }
                }
            }
        }
    }

    private fun requestAudioRecordingPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.RECORD_AUDIO), 0
        )
    }
}