package com.dov.navigationformlistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dov.navigationformlistcompose.ui.theme.NavigationFormListComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationFormListComposeTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("calculator/{userName}") { backStackEntry ->
            CalculatorScreen( backStackEntry.arguments?.getString("userName") ?: "", navController)
        }
        composable("history") { HistoryScreen(navController) }
    }
}

object ApplicationData {
    val operationsHistory = mutableListOf<String>()
}
