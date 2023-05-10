package com.example.voicerecorder.ui.screens.recordings

import androidx.lifecycle.ViewModel
import com.example.voicerecorder.database.AudioRecord
import com.example.voicerecorder.repo.RecorderRepo
import kotlinx.coroutines.flow.Flow

class RecordingsViewModel(private val repository: RecorderRepo) : ViewModel() {

    // TODO rewrite to viewModelScope/ LaunchEffect?
    fun getRecords(): Flow<List<AudioRecord>> {
        return repository.getRecords()
    }
}