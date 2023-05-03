package com.example.voicerecorder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.voicerecorder.AudioRecord
import com.example.voicerecorder.ui.screens.playing.PlayingScreen
import com.example.voicerecorder.ui.screens.recording.RecordingScreen
import com.example.voicerecorder.ui.screens.recordings.RecordingsScreen
import com.google.gson.Gson

//TODO strings
@Composable
fun NavigationSetup(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Recording.route) {
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
//            TODO move to fun
            navBackStackEntry.arguments?.getString("audiorecord").let { json ->
                val audioRecord = Gson().fromJson(
                    json, AudioRecord::class.java
                )
                PlayingScreen(audioRecord = audioRecord)
            }
        }
    }
}