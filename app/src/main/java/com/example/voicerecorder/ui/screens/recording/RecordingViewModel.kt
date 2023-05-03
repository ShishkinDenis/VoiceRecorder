package com.example.voicerecorder.ui.screens.recording

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

//TODO rename
class RecordingViewModel : ViewModel() {
    var isRecording = mutableStateOf(false)
}