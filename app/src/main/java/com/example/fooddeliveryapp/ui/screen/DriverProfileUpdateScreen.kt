package com.example.driverapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fooddeliveryapp.data.entity.Driver

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverProfileUpdateScreen(navController: NavController, driver: Driver = sampleDriver()) {
    var name by remember { mutableStateOf(driver.fullName) }
    var phone by remember { mutableStateOf(driver.phone) }
    var email by remember { mutableStateOf(driver.email) }
    var vehicle by remember { mutableStateOf(driver.vehicle) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Update Profile") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Full Name") })
            OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") })
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            OutlinedTextField(value = vehicle, onValueChange = { vehicle = it }, label = { Text("Vehicle") })

            Spacer(Modifier.height(20.dp))

            Button(onClick = {
                // save updated driver to DB
                navController.popBackStack()
            }) {
                Text("Save Changes")
            }
        }
    }
}

