package com.soquipment.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.soquipment.presentation.R
import com.soquipment.presentation.ui.theme.PrimaryColor

@Composable
fun ClockIcon() {
    Icon(
        painter = painterResource(R.drawable.ic_clock),
        tint = PrimaryColor,
        contentDescription = "clock-icon"
    )
}

@Composable
fun CloseIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Icon(
        modifier = modifier.clickable(onClick = onClick),
        imageVector = Icons.Default.Close,
        contentDescription = "close-icon"
    )
}