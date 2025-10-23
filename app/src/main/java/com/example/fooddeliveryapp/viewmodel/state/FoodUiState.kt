package com.example.fooddeliveryapp.viewmodel.state

data class FoodUiState (
    val id: Long = 0,
    val name: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val vendorName: String = ""
)