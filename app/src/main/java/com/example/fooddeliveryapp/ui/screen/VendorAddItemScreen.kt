package com.example.fooddeliveryapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.data.entity.FoodItem
import com.example.fooddeliveryapp.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendorAddItemScreen(
    navController: NavController,
    viewModel: FoodViewModel = viewModel(),
    vendorId: Long
) {

    var itemName by remember { mutableStateOf("") }
    var itemDescription by remember { mutableStateOf("") }
    var itemPrice by remember { mutableStateOf("") }
    var itemImageUrl by remember { mutableStateOf("") }

    var selectedCategory by remember { mutableStateOf("Select Category") }
    var categoryExpanded by remember { mutableStateOf(false) }

    var selectedTag by remember { mutableStateOf("NEW") }
    var availability by remember { mutableStateOf("AVAILABLE") }

    val categories = listOf("Burger", "Wrap", "Pizza", "Coffee", "Drink", "Dessert")

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { TopAppBar(title = { Text("ADD NEW ITEM") }) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = itemName,
                onValueChange = { itemName = it },
                label = { Text("Item Name") },
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = categoryExpanded,
                onExpandedChange = { categoryExpanded = it }
            ) {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Category") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = categoryExpanded,
                    onDismissRequest = { categoryExpanded = false }
                ) {
                    categories.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                selectedCategory = it
                                categoryExpanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = itemPrice,
                onValueChange = { itemPrice = it },
                label = { Text("Price (e.g., 39.99)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = itemDescription,
                onValueChange = { itemDescription = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = itemImageUrl,
                onValueChange = { itemImageUrl = it },
                label = { Text("Image URL") },
                modifier = Modifier.fillMaxWidth()
            )

            // Special Tag
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("NEW", "POPULAR", "LIMITED TIME").forEach { tag ->
                    FilterChip(
                        selected = selectedTag == tag,
                        onClick = { selectedTag = tag },
                        label = { Text(tag) }
                    )
                }
            }

            // Availability
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf("AVAILABLE", "SOLD OUT").forEach { status ->
                    Row {
                        RadioButton(
                            selected = availability == status,
                            onClick = { availability = status }
                        )
                        Text(status)
                    }
                }
            }

            Button(
                onClick = {
                    val price = itemPrice.toDoubleOrNull()
                    if (itemName.isBlank() || itemDescription.isBlank() || price == null ||
                        selectedCategory == "Select Category"
                    )
//                    {
//                        LaunchedEffect( Unit) {
//                            snackbarHostState.showSnackbar("Please fill all required fields correctly")
//                        }
//                        return@Button
//                    }

                    if (price != null)    {
                    viewModel.insertFood(
                        FoodItem(
                            name = itemName,
                            description = itemDescription,
                            price = price,
                            imageUrl = itemImageUrl,
                            vendorId = vendorId,
                            category = selectedCategory,
                            tag = selectedTag,
                            availability = availability
                        )
                    )}
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("SAVE")
            }

            OutlinedButton(
                onClick = {
                    itemName = ""
                    itemDescription = ""
                    itemPrice = ""
                    itemImageUrl = ""
                    selectedCategory = "Select Category"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("CLEAR")
            }
        }
    }
}