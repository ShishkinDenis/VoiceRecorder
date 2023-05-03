package com.example.voicerecorder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.voicerecorder.AudioRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface AudioRecordDao {

//TODO db name
    @Query("SELECT * FROM audioRecords")
    fun getRecords(): Flow<List<AudioRecord>>

    @Insert
    fun insert(vararg audioRecord: AudioRecord)
}