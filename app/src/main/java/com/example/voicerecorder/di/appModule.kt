package com.example.voicerecorder.di

import androidx.room.Room
import com.example.voicerecorder.audiorecorder.AndroidAudioRecorder
import com.example.voicerecorder.database.AppDatabase
import com.example.voicerecorder.database.AudioRecordDao
import com.example.voicerecorder.repo.RecorderRepo
import com.example.voicerecorder.repo.RecorderRepoImpl
import com.example.voicerecorder.ui.screens.recording.RecordingViewModel
import com.example.voicerecorder.ui.screens.recordings.RecordingsViewModel
import com.example.voicerecorder.utils.AppConstants.DATABASE_NAME
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
//    TODO move building to AppDatabase
    single {
        Room.databaseBuilder(
            get(), AppDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
    //TODO move fun
    fun provideDao(appDatabase: AppDatabase): AudioRecordDao {
        return appDatabase.dao
    }
    single { provideDao(get()) }
    single<RecorderRepo> { RecorderRepoImpl(get()) }
    single { AndroidAudioRecorder(get(), get()) }

    viewModel { RecordingsViewModel(get()) }
    viewModel { RecordingViewModel() }
}