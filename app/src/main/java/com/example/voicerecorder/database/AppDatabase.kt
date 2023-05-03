package com.example.voicerecorder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.voicerecorder.AudioRecord

@Database(entities = [AudioRecord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: AudioRecordDao
}