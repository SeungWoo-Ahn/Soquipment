package com.soquipment.presentation.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.soquipment.presentation.ui.theme.PrimaryColor

@Composable
fun CircleChart(progress: Float) {
    CircularProgressIndicator(
        modifier = Modifier.size(80.dp),
        progress = { progress },
        color = PrimaryColor,
        trackColor = Color.LightGray,
        strokeWidth = 5.dp
    )
}