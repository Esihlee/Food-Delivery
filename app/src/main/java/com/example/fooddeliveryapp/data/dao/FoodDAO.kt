package com.example.fooddeliveryapp.data.dao


import androidx.room.*
import com.example.fooddeliveryapp.data.entity.FoodItem

@Dao
interface FoodDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(item: FoodItem)

    @Query("SELECT * FROM food_items")
    suspend fun getAllFoods(): List<FoodItem>

    @Delete
    suspend fun deleteFood(item: FoodItem)
}