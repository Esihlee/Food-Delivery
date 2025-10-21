package com.example.fooddeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.driverapp.ui.screens.DriverDashboardScreen
import com.example.driverapp.ui.screens.DriverOrderScreen
import com.example.driverapp.ui.screens.DriverProfileScreen
import com.example.driverapp.ui.screens.DriverProfileUpdateScreen

class MainAct : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Surface(color = MaterialTheme.colorScheme.background) {
                NavHost(navController = navController, startDestination = "dashboard") {
                    composable("dashboard") { DriverDashboardScreen(navController) }
                    composable("orders") { DriverOrderScreen(navController) }
                    composable("profile") { DriverProfileScreen(navController) }
                    composable("updateProfile") { DriverProfileUpdateScreen(navController) }
                }
            }
        }
    }
}