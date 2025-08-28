package com.example.fooddeliveryapp.ui.screen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun VendorOrdersScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Orders") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Your Orders", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(24.dp))

            // TODO: Replace with LazyColumn displaying real orders
            Text("Order #1 - Pending")
            Text("Order #2 - Completed")

            Spacer(Modifier.height(32.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Back to Welcome")
            }
        }
    }
}