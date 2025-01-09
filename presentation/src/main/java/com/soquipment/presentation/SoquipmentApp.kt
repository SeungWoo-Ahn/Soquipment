package com.soquipment.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.soquipment.presentation.navigation.Screen
import com.soquipment.presentation.ui.screen.home.HomeRoute
import com.soquipment.presentation.ui.screen.payment.PaymentRoute

@Composable
fun SoquipmentApp(
    modifier: Modifier = Modifier,
    appState: SoquipmentAppState,
) {
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeRoute(
                scope = appState.coroutineScope,
                movePayment = { navController.navigate(Screen.Payment) }
            )
        }
        composable<Screen.Payment> {
            PaymentRoute(
                moveTracking = { navController.navigate(Screen.Tracking) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}