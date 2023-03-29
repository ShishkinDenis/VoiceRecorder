package com.example.voicerecorder

import android.util.Log
import com.example.voicerecorder.ExampleRepository

class ExampleRepositoryImpl : ExampleRepository {

    override fun doSomething() {
        Log.d("Example", "Do something")
    }
}