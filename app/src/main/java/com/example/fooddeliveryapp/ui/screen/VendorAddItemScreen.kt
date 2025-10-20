package com.example.fooddeliveryapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fooddeliveryapp.data.entity.FoodItem
import com.example.fooddeliveryapp.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendorAddItemScreen(
    navController: NavController,
    viewModel: FoodViewModel,
    vendorId: Long,

) {
    var itemName by remember { mutableStateOf("") }
    var itemDescription by remember { mutableStateOf("") }
    var itemPrice by remember { mutableStateOf("") }
    var itemImageUrl by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add Menu Item") })
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
                value = itemName,
                onValueChange = { itemName = it },
                label = { Text("Item Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = itemDescription,
                onValueChange = { itemDescription = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = itemPrice,
                onValueChange = { itemPrice = it },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = itemImageUrl,
                onValueChange = { itemImageUrl = it },
                label = { Text("Image URL") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (itemName.isNotBlank() && itemPrice.isNotBlank()) {
                        val price = itemPrice.toDoubleOrNull() ?: 0.0
                        val foodItem = FoodItem(
                            name = itemName,
                            description = itemDescription,
                            price = price,
                            imageUrl = itemImageUrl,
                            vendorId = vendorId
                        )

                        viewModel.insertFood(foodItem)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Item")
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel")
            }
        }
    }
}
