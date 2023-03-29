package com.example.voicerecorder.di

import com.example.voicerecorder.ExampleRepository
import com.example.voicerecorder.ExampleRepositoryImpl
import com.example.voicerecorder.ui.RecordingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ExampleRepository> { ExampleRepositoryImpl() }
    viewModel { RecordingViewModel(get()) }
}