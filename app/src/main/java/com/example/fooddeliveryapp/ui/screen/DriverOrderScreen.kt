package com.example.driverapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fooddeliveryapp.data.entity.Order

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverOrderScreen(navController: NavController, orders: List<Order> = sampleOrders()) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Incoming Orders") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(orders) { order ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Customer: ${order.custName}", style = MaterialTheme.typography.titleMedium)
                        Text("Price: R${order.price}")
                        Text("Location: ${order.location}")
                        Text("Distance: ${order.distance} m")
                        Text("ETA: ${order.estTime} mins")
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = { /* Accept Order */ }) { Text("Accept") }
                            OutlinedButton(onClick = { /* Decline Order */ }) { Text("Decline") }
                        }
                    }
                }
            }
        }
    }
}

fun sampleOrders() = listOf(
    Order(1, "Ayanda Sibeko","Classic beef Burger, Small Fries, Coke", 85.0, "Hanover Str, District Six", 350, 15, "Pending"),
    Order(2, "Lonwabo Mnyamana", "Pizza + 2 Drinks",120.0, "Hanover Str, District Six", 200, 23, "Pending")
)
