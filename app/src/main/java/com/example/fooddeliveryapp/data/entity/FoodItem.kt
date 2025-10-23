package com.example.fooddeliveryapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_items")
data class FoodItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val vendorId: Long,
    val category: String,
    val tag: String,
    val availability: String
)
