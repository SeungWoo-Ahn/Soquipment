package com.soquipment.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SoTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBack: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(
                top = 40.dp,
                bottom = 10.dp,
                start = 20.dp,
                end = 20.dp
            ),
    ) {
        BackIcon(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onBack
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}