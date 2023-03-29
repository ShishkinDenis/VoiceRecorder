package com.example.voicerecorder.navigation

//TODO strings
sealed class Screen(val route: String) {
    object Recording : Screen("recording")
    object Playing : Screen("playing")
}
