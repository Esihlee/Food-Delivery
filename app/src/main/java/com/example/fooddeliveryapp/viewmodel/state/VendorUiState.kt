package com.example.fooddeliveryapp.viewmodel.state

import com.example.fooddeliveryapp.data.entity.Vendor

data class VendorUiState (
    val id: Long = 0,
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val isOnline: Boolean = false,
    val totalOrders: Int = 0,
    val vendor: Vendor? = null,
    val isLoading: Boolean = true
)