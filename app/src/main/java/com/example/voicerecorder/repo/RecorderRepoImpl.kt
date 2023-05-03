package com.example.voicerecorder.repo

import com.example.voicerecorder.AudioRecord
import com.example.voicerecorder.database.AudioRecordDao
import kotlinx.coroutines.flow.Flow

class RecorderRepoImpl(
    private val dao: AudioRecordDao,
) : RecorderRepo {

    override suspend fun insertRecording(recording: AudioRecord) {
        dao.insert(recording)
    }

    override fun getRecords(): Flow<List<AudioRecord>> {
        return dao.getRecords()
    }
}