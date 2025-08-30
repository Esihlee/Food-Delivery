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
import com.example.fooddeliveryapp.ui.screen.VendorAddItemScreen
import com.example.fooddeliveryapp.ui.screen.VendorOrdersScreen
import com.example.fooddeliveryapp.ui.screen.VendorUpdateScreen
import com.example.fooddeliveryapp.ui.screen.VendorWelcomeScreen

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

            LoginScreen(navController)
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
        // 🔹 Vendor routes
        composable("vendor_welcome") {
            VendorWelcomeScreen(navController)
        }
        composable("vendor_orders") {
            VendorOrdersScreen(navController)
        }
        composable("vendor_add_item") {
            VendorAddItemScreen(navController)
        }
        composable("vendor_update") {
            VendorUpdateScreen(navController)
        }
//
//        // 🔹 Student Home
//        composable("student_home") {
//            StudentHomeScreen(navController)
//        }
//
//        // 🔹 Lecturer Home
//        composable("lecturer_home") {
//            LecturerHomeScreen(navController)
//        }
//
//        // 🔹 Vendor Home
//        composable("vendor_home") {
//            VendorHomeScreen(navController)
//        }
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
