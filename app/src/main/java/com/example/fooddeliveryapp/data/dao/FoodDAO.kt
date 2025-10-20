package com.example.fooddeliveryapp.data.dao


import androidx.room.*
import com.example.fooddeliveryapp.data.entity.FoodItem
import com.example.fooddeliveryapp.data.entity.Vendor

@Dao
interface FoodDAO {
    //Food
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(item: FoodItem)

    @Query("SELECT * FROM food_items")
    suspend fun getAllFoods(): List<FoodItem>

    @Query("SELECT * FROM food_items WHERE vendorId = :vendorId")
    suspend fun getFoodsByVendor(vendorId: Long): List<FoodItem>

    @Delete
    suspend fun deleteFood(item: FoodItem)

    //Vendor
    @Query("SELECT * FROM vendors WHERE vendorId = :id LIMIT 1")
    suspend fun getVendorById(id: Long): Vendor
    @Query("UPDATE vendors SET isOnline = :isOnline WHERE vendorId = :id")
    suspend fun updateVendorStatus(id: Long, isOnline: Boolean)
    @Query("SELECT * FROM vendors")
    suspend fun getAllVendors(): List<Vendor>


}