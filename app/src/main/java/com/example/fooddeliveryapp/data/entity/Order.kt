package com.example.fooddeliveryapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val custName: String,
    val items: String,
    val price: Double,
    val location: String,
    val distance: Int,
    val estTime: Int,
    var status: String = "Pending",
    var driverID: Int? = null
)
