package com.example.voicerecorder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.voicerecorder.ui.RecordingScreen
import com.example.voicerecorder.ui.PlayingScreen

@Composable
fun NavigationSetup(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Recording.route) {
        composable(BottomNavItem.Recording.route) {
            RecordingScreen()
        }
        composable(BottomNavItem.Playing.route) {
            PlayingScreen()
        }
    }
}