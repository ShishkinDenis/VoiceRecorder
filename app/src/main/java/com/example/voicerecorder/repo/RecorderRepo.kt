package com.example.voicerecorder.repo

import com.example.voicerecorder.database.AudioRecord
import kotlinx.coroutines.flow.Flow

interface RecorderRepo {

    suspend fun insertRecording(recording: AudioRecord)

    fun getRecords(): Flow<List<AudioRecord>>
}