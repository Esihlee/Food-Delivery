package com.example.fooddeliveryapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drivers")
data class Driver(
    @PrimaryKey(autoGenerate = true) val driverID: Int = 0,
    val fullName: String,
    val phone: String,
    val email: String,
    val vehicle: String
)
