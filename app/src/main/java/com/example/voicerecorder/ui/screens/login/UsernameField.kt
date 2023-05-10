package com.example.voicerecorder.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.voicerecorder.R

@Composable
fun UsernameField(
    username: String,
    onUsernameChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    Column(
        modifier = Modifier.padding(
            bottom = if (isError) {
                0.dp
            } else {
                10.dp
            }
        )
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.username)) },
            value = username,
            onValueChange = onUsernameChange,
            isError = errorMessage != ""
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}