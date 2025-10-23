package com.example.fooddeliveryapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Order(
    val orderId: Int,
    val customerName: String,
    val itemsOrdered: String,
    val totalPrice: String,
    val deliveryLocation: String,
    val status: String,
    val modifications: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendorOrdersScreen(navController: NavController) {

    var orders by remember {
        mutableStateOf(
            listOf(
                Order(1001, "Alex J.", "1x Beef Burger, 1x Coke", "R80", "Engineering, Rm 102", "New", "No garnish"),
                Order(1002, "Zoe L.", "2x Chicken Tacos", "R90", "Lecture Hall 5", "Preparing", "N/A"),
                Order(1003, "Mike T.", "1x Fries(Large), 1x Sprite", "R60", "Campus Library", "Out for delivery", "Barbeque Sauce"),
                Order(1004, "Sam K.", "1x Pizza Slice, 1x Coffee", "R70", "Commerce Office 204", "Completed", "Extra Cheese"),
                Order(1005, "Daisy P.", "1x Wrap, 2x Amagwinya", "R85", "Field (Piazza)", "New", "N/A"),
                Order(1006, "Ben H.", "1x Margherita Pizza", "R100", "Design, Room 201", "Preparing", "Pineapple"),
                Order(1007, "Sihle M.", "1x Chips, 1x Burger", "R95", "Engineering, Lab 10", "Out for delivery", "No sauce on fries"),
                Order(1008, "Iyazi X.", "2x Amagwinya, 1x Tea", "R45", "Field (Piazza)", "Completed", "N/A"),
                Order(1009, "Keanu R.", "1x Chicken Wrap, 1x Water", "R65", "Admin Building", "New", "No tomato"),
                Order(1010, "Liam S.", "1x Taco, 1x Fries, 1x Coke", "R105", "Pick-up Point A", "Preparing", "N/A")
            )
        )
    }

    fun updateStatus(order: Order, newStatus: String) {
        orders = orders.map {
            if (it.orderId == order.orderId) it.copy(status = newStatus) else it
        }
    }

    fun sendToDelivery(order: Order) {
        updateStatus(order, "Out for delivery")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ORDERS",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                        color = Color(0xFFd16e12)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(orders) { order ->
                    OrderRow(
                        order = order,
                        onSendToDelivery = { sendToDelivery(order) },
                        onStatusChange = { o, newStatus -> updateStatus(o, newStatus) }
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Back to Welcome")
            }
        }
    }
}

@Composable
fun StatusChip(status: String) {
    val chipColor = when(status) {
        "New" -> Color(0xFFE91E63)
        "Preparing" -> Color(0xFFFFA000)
        "Out for delivery" -> Color(0xFF1976D2)
        "Completed" -> Color(0xFF4CAF50)
        else -> Color.Gray
    }

    Surface(
        color = chipColor,
        shadowElevation = 4.dp,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = status,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@Composable
fun OrderRow(
    order: Order,
    onSendToDelivery: (Order) -> Unit,
    onStatusChange: (Order, String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFAF0))
    ) {
        Column(Modifier.padding(12.dp)) {
            Text("Order #${order.orderId}", fontWeight = FontWeight.Bold)
            Text("Customer: ${order.customerName}")
            Text("Items: ${order.itemsOrdered}")
            Text("Total: ${order.totalPrice}", fontWeight = FontWeight.SemiBold)
            Text("Location: ${order.deliveryLocation}")
            Text("Notes: ${order.modifications}")

            Spacer(Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatusChip(order.status)

                Button(
                    onClick = {
                        when (order.status) {
                            "New" -> onStatusChange(order, "Preparing")
                            "Preparing" -> onStatusChange(order, "Out for delivery")
                            "Out for delivery" -> onStatusChange(order, "Completed")
                        }
                    },
                    enabled = order.status != "Completed"
                ) {
                    Text("Next Status")
                }
            }

            if (order.status == "Completed") {
                Spacer(Modifier.height(8.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF388E3C)),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSendToDelivery(order) }
                ) {
                    Text("SEND TO DELIVERY")
                }
            }
        }
    }
}
