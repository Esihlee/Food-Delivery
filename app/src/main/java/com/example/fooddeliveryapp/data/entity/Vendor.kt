package com.example.fooddeliveryapp.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vendors")
data class Vendor(
    @PrimaryKey(autoGenerate = true) val vendorId: Long = 0,
    val name: String,
    val email: String,
    val phone: String,
    val isOnline: Boolean = false
)
