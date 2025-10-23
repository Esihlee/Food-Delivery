package com.example.fooddeliveryapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.viewmodel.FoodViewModel
import com.example.fooddeliveryapp.data.entity.FoodItem
import com.example.fooddeliveryapp.viewmodel.VendorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendorUpdateScreen(
    navController: NavController,
    vendorId: Long,
    foodViewModel: FoodViewModel = viewModel(),
    vendorViewModel: VendorViewModel = viewModel()
) {

    var vendorName by remember { mutableStateOf("") }
    var vendorPhone by remember { mutableStateOf("") }
    var vendorEmail by remember { mutableStateOf("") }
    var foodItems by remember { mutableStateOf(listOf<FoodItem>()) }

    val vendor by vendorViewModel.currentVendor.observeAsState()

    LaunchedEffect(vendorId) {
        vendorViewModel.loadVendor(vendorId)
        vendor?.let {
            vendorName = it.name
            vendorPhone = it.phone
            vendorEmail = it.email
        }
    }

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
            OutlinedTextField(value = vendorName, onValueChange = { vendorName = it }, label = { Text("Vendor Name") })
            OutlinedTextField(value = vendorPhone, onValueChange = { vendorPhone = it }, label = { Text("Phone") })
            OutlinedTextField(value = vendorEmail, onValueChange = { vendorEmail = it }, label = { Text("Email") })

            Button(
                onClick = {
                    vendor?.let { v ->
                        vendorViewModel.updateVendor(
                            v.copy(name = vendorName, phone = vendorPhone, email = vendorEmail)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Save Profile") }

            Button(
                onClick = {
                    foodViewModel.getFoodsByVendor(vendorId) { foodItems = it }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Load Menu Items") }

            LazyColumn {
                items(foodItems) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(item.name)
                                Text("R${item.price}")
                            }

                            Row {
                                TextButton(
                                    onClick = {
                                        navController.navigate("vendor_update")
                                    }
                                ) { Text("Update") }

                                Spacer(modifier = Modifier.width(8.dp))

                                TextButton(
                                    onClick = {
                                        foodViewModel.deleteFood(item) {
                                            foodViewModel.getFoodsByVendor(vendorId) { foodItems = it }
                                        }
                                    },
                                    colors = ButtonDefaults.textButtonColors(MaterialTheme.colorScheme.error)
                                ) { Text("Delete") }
                            }
                        }
                    }
                }
            }
        }
    }
}
