package com.example.fooddeliveryapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun VendorUpdateScreen(navController: NavController) {
    var vendorName by remember { mutableStateOf("") }
    var vendorPhone by remember { mutableStateOf("") }
    var vendorEmail by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Update Vendor Profile") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = vendorName,
                onValueChange = { vendorName = it },
                label = { Text("Vendor Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = vendorPhone,
                onValueChange = { vendorPhone = it },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = vendorEmail,
                onValueChange = { vendorEmail = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    // TODO: Save vendor update
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Profile")
            }

            Button(onClick = { navController.popBackStack() }) {
                Text("Cancel")
            }
        }
    }
}