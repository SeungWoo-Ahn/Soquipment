package com.soquipment.presentation.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.soquipment.presentation.ui.theme.PrimaryColor
import kotlinx.coroutines.delay

@Composable
fun CircleChart(progress: Float) {
    val progressState = remember { mutableFloatStateOf(0f) }
    val progressAnim = animateFloatAsState(
        targetValue = progressState.value,
        label = "progress-anim"
    )
    LaunchedEffect(Unit) {
        delay(300)
        progressState.value += progress
    }
    CircularProgressIndicator(
        modifier = Modifier.size(80.dp),
        progress = { progressAnim.value },
        color = PrimaryColor,
        trackColor = Color.LightGray,
        strokeWidth = 5.dp
    )
}