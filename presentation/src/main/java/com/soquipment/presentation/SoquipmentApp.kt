package com.soquipment.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.soquipment.presentation.navigation.Screen

@Composable
fun SoquipmentApp(
    modifier: Modifier = Modifier,
    appState: SoquipmentAppState,
) {
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Main
    ) {
        composable<Screen.Main> {

        }
    }
}