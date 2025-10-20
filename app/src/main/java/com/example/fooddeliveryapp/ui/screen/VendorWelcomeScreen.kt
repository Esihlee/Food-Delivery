package com.example.fooddeliveryapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fooddeliveryapp.R // ✅ make sure this is imported

@Composable
fun VendorWelcomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // ✅ Background image
        Image(
            painter = painterResource(id = R.drawable.vendor), // replace with your image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // ✅ Foreground content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("WELCOME VENDOR", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)

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
