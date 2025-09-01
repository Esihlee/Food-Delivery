package com.example.fooddeliveryapp.data.dao


import androidx.room.*
import com.example.fooddeliveryapp.data.entity.FoodItem
import com.example.fooddeliveryapp.data.entity.Vendor

@Dao
interface FoodDAO {
    //Food
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: FoodItem)

    @Query("SELECT * FROM food_items")
    suspend fun getAllFoods(): List<FoodItem>

    @Delete
    suspend fun deleteFood(food: FoodItem)

    @Query("SELECT * FROM food_items WHERE vendorId = :vendorId")
    suspend fun getFoodsByVendor(vendorId: Long): List<FoodItem>
}