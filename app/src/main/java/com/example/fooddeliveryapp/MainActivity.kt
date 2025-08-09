package com.example.fooddeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.fooddeliveryapp.ui.screen.WelcomeScreen
import com.example.fooddeliveryapp.ui.screen.LoginScreen
import com.example.fooddeliveryapp.ui.screen.SignupScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(
                navToLogin = { navController.navigate("login") },
                navToSignup = { navController.navigate("signup") }
            )
        }
        composable("login") {
            LoginScreen(navController)
        }

        composable("signup") {
            SignupScreen(navController)
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun WelcomeScreenPreview() {
        WelcomeScreen(
            navToLogin = {},  // empty lambdas for preview
            navToSignup = {}
        )
    }
}
