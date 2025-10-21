package com.example.driverapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fooddeliveryapp.data.entity.Driver

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverProfileScreen(navController: NavController, driver: Driver = sampleDriver()) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Driver Profile") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Name: ${driver.fullName}", style = MaterialTheme.typography.bodyLarge)
            Text("Phone: ${driver.phone}")
            Text("Email: ${driver.email}")
            Text("Vehicle: ${driver.vehicle}")

            Spacer(Modifier.height(20.dp))

            Button(onClick = { navController.navigate("updateProfile") }) {
                Text("Update Profile")
            }
        }
    }
}

fun sampleDriver() = Driver(
    driverID = 1,
    fullName = "Maxwell Payne",
    phone = "+27 60 458 9486",
    email = "maxpayne.com",
    vehicle = "Toyota PRIUS - CAA 459 843"
)
