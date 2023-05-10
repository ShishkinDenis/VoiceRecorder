package com.example.voicerecorder.ui.screens.recordings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.voicerecorder.database.AudioRecord
import com.example.voicerecorder.R
import com.example.voicerecorder.navigation.Screen
import com.example.voicerecorder.playback.AndroidAudioPlayer
import com.example.voicerecorder.ui.composable.RoundButton
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

//TODO change to stop icon when playing is finished
@Composable
fun RecordItem(audioRecord: AudioRecord, navController: NavController) {

    val context = LocalContext.current
    var isPlaying by rememberSaveable {
        mutableStateOf(false)
    }
    val player by lazy {
        AndroidAudioPlayer(context) { isPlaying = false }
    }

    Row(
        modifier = Modifier
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        RoundButton(
            iconId = if (isPlaying) R.drawable.baseline_stop_24 else R.drawable.ic_play,
            iconSize = 30.dp,
            color = Color.LightGray
        ) {
            if (isPlaying) {
                player.stop()
            } else {
                player.play(audioRecord)
            }
            isPlaying = !isPlaying
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier
            .clickable {
//                TODO move to fun
                val audioRecordJson = Gson().toJson(audioRecord)
                val encodedAudioRecord =
                    URLEncoder.encode(audioRecordJson, StandardCharsets.UTF_8.toString())
                navController.navigate(Screen.Playing.route + "/${encodedAudioRecord}")
            }
            .fillMaxWidth()) {
            Text(
                text = audioRecord.filename
            )
            Text(
                text = audioRecord.date
            )
        }
    }
    Divider()
}



