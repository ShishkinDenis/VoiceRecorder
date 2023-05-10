package com.example.voicerecorder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.voicerecorder.database.AudioRecord
import com.example.voicerecorder.ui.screens.login.LoginScreen
import com.example.voicerecorder.ui.screens.playing.PlayingScreen
import com.example.voicerecorder.ui.screens.recording.RecordingScreen
import com.example.voicerecorder.ui.screens.recordings.RecordingsScreen
import com.google.gson.Gson

//TODO strings
@Composable
fun NavigationSetup(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(BottomNavItem.Recording.route) {
            RecordingScreen()
        }
        composable(BottomNavItem.Records.route) {
            RecordingsScreen(navController = navController)
        }
        composable(
            Screen.Playing.route + "/{audiorecord}",
            arguments = listOf(
                navArgument("audiorecord") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val audioRecord = deserializeFromJson(navBackStackEntry)
            PlayingScreen(audioRecord = audioRecord)
        }
    }
}

private fun deserializeFromJson(navBackStackEntry: NavBackStackEntry): AudioRecord {
    return Gson().fromJson(
        navBackStackEntry.arguments?.getString("audiorecord"), AudioRecord::class.java
    )
}