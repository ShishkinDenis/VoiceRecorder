package com.example.voicerecorder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audioRecords")
data class AudioRecord(
    var filename: String,
    var filePath: String? = null,
    var date: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
