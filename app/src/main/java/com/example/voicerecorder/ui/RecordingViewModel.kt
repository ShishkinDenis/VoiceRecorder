package com.example.voicerecorder.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.voicerecorder.ExampleRepository

class RecordingViewModel(private val repository: ExampleRepository) : ViewModel() {

    var selected = mutableStateOf(false)

    fun doExampleFunc() {
        repository.doSomething()
    }
}