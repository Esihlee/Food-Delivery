package com.example.fooddeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryapp.data.db.AppDatabase
import com.example.fooddeliveryapp.ui.screen.LoginScreen
import com.example.fooddeliveryapp.ui.screen.MenuScreen
import com.example.fooddeliveryapp.ui.screen.SignupScreen
import com.example.fooddeliveryapp.ui.screen.WelcomeScreen

@Composable
fun AppNavigation(db: AppDatabase) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(
                navToLogin = { navController.navigate("login") },
                navToSignup = { navController.navigate("signup") }
            )
        }

        composable("login") {
            LoginScreen(
                navController = navController,
                userDao = db.userDao(),
                onLoginSuccess = { enteredUsername ->
                    navController.navigate("menu") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("signup") {
            SignupScreen(
                navController = navController,
                userDao = db.userDao()
            )
        }

        // MenuScreen no longer takes parameters
        composable("menu") {
            MenuScreen()
        }

        // Add other screens here as needed
    }
}

class MainActivity : ComponentActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase.getDatabase(this)

        setContent {
            AppNavigation(db = db)
        }
    }
}
