package com.example.mobile_programming_group9_smart_campusproject_2026

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobile_programming_group9_smart_campusproject_2026.ui.screens.*
import com.example.mobile_programming_group9_smart_campusproject_2026.ui.theme.SmartCampusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemDark = isSystemInDarkTheme()
            var isDarkMode by remember { mutableStateOf(systemDark) }
            
            SmartCampusTheme(darkTheme = isDarkMode) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(isDarkMode = isDarkMode, onThemeToggle = { isDarkMode = !isDarkMode })
                }
            }
        }
    }
}

@Composable
fun AppNavigation(isDarkMode: Boolean, onThemeToggle: () -> Unit) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable(
            route = "details/{name}/{from}/{to}/{time}/{phone}/{price}/{extraInfo}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("from") { type = NavType.StringType },
                navArgument("to") { type = NavType.StringType },
                navArgument("time") { type = NavType.StringType },
                navArgument("phone") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType },
                navArgument("extraInfo") { type = NavType.StringType; defaultValue = "none" }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val from = backStackEntry.arguments?.getString("from") ?: ""
            val to = backStackEntry.arguments?.getString("to") ?: ""
            val time = backStackEntry.arguments?.getString("time") ?: ""
            val phone = backStackEntry.arguments?.getString("phone") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            val extraInfo = backStackEntry.arguments?.getString("extraInfo") ?: "none"
            
            RideDetailsScreen(navController, name, from, to, time, phone, price, extraInfo)
        }
        composable(
            route = "price-review/{from}/{to}/{price}",
            arguments = listOf(
                navArgument("from") { type = NavType.StringType },
                navArgument("to") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val from = backStackEntry.arguments?.getString("from") ?: ""
            val to = backStackEntry.arguments?.getString("to") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            PriceReviewScreen(navController, from, to, price)
        }
        composable(
            route = "success/{from}/{to}/{price}",
            arguments = listOf(
                navArgument("from") { type = NavType.StringType },
                navArgument("to") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val from = backStackEntry.arguments?.getString("from") ?: ""
            val to = backStackEntry.arguments?.getString("to") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            BookingSuccessScreen(navController, from, to, price)
        }
        composable(
            route = "call/{name}/{phone}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("phone") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val phone = backStackEntry.arguments?.getString("phone") ?: ""
            CallScreen(navController, name, phone)
        }
        composable("tracking") { TrackingScreen(navController) }
        composable("profile") { ProfileScreen(navController, isDarkMode, onThemeToggle) }
    }
}
