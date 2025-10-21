package com.example.driverapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverDashboardScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Driver Dashboard") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { navController.navigate("orders") }) {
                Text("View Incoming Orders")
            }
            Button(onClick = { navController.navigate("profile") }) {
                Text("View Profile")
            }
        }
    }
}
