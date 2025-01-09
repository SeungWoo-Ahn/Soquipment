package com.soquipment.presentation.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String
) {
    GlideImage(
        modifier = modifier,
        model = url,
        contentScale = ContentScale.FillBounds,
        contentDescription = "${url}-image"
    )
}