package com.example.fooddeliveryapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun VendorWelcomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("WELCOME VENDOR", fontSize = 28.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(24.dp))

            Button(onClick = { navController.navigate("vendor_orders") }) {
                Text("ORDERS")
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = { navController.navigate("vendor_add_item") }) {
                Text("ADD ITEM")
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = { navController.navigate("vendor_update") }) {
                Text("UPDATE")
            }
        }
    }
}

