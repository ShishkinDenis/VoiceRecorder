package com.example.voicerecorder.ui.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.voicerecorder.R

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    iconSize: Dp = 55.dp,
    color: Color = Color.Gray,
    onClick: () -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(iconId),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .background(color = color, shape = CircleShape)
                .size(iconSize)
                .clickable {
                    onClick()
                },
        )
    }
}

@Preview
@Composable
fun RoundButtonPreview() {
    RoundButton(
        iconId = R.drawable.ic_play,
        iconSize = 60.dp,
        color = Color.Gray,
    ) {
    }
}