package com.example.voicerecorder.ui.screens.recordings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.voicerecorder.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecordingsScreen(
    navController: NavHostController,
    viewModel: RecordingsViewModel = koinViewModel()
) {

    val recordings = viewModel.getRecords().collectAsState(initial = emptyList())
    if (recordings.value.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = stringResource(id = R.string.no_records))
        }
    } else {
        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(
                items = recordings.value
            ) {
                RecordItem(
                    audioRecord = it,
                    navController = navController
                )
            }
        }
    }
}
