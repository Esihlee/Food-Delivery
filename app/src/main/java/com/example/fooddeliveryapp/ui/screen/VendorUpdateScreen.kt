package com.example.fooddeliveryapp.ui.screen

import android.R.attr.vendor
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendorUpdateScreen(navController: NavController, vendorId: Long,foodViewModel: FoodViewModel = viewModel()) {
    var vendorName by remember { mutableStateOf("") }
    var vendorPhone by remember { mutableStateOf("") }
    var vendorEmail by remember { mutableStateOf("") }

    var menuItems by remember { mutableStateOf(listOf<FoodItem>())

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
                    val updatedVendor = vendor.copy(
                        name = vendorName,
                        phone = vendorPhone,
                        email = vendorEmail
                    )
                    viewModel.updateVendor(updatedVendor)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Profile")
            }

            Button(
                onClick = {
                    // Retrieve vendor menu items
                    foodViewModel.getFoodsByVendor(vendorId) { items ->
                        menuItems = items
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Load Menu Items")
            }

            LazyColumn {
                items(menuItems) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
                                Text(text = "R${item.price}", style = MaterialTheme.typography.bodyMedium)
                            }

                            Button(
                                onClick = {
                                    foodViewModel.deleteFood(item) {
                                        foodViewModel.getFoodsByVendor(vendorId) { items ->
                                            menuItems = items // refresh after delete
                                        }
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Cancel")
            }
        }
    }
}


