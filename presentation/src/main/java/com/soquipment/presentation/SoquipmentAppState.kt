package com.soquipment.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

class SoquipmentAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
)

@Composable
fun rememberSoquipmentAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) : SoquipmentAppState {
    return remember(
        navController,
        coroutineScope
    ) {
        SoquipmentAppState(
            navController = navController,
            coroutineScope = coroutineScope,
        )
    }
}