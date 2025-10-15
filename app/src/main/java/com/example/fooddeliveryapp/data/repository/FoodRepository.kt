package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.dao.FoodDAO
import com.example.fooddeliveryapp.data.entity.FoodItem
import com.example.fooddeliveryapp.data.entity.Vendor

class FoodRepository(private val foodDao: FoodDAO) {
    //Food
    suspend fun getAll(): List<FoodItem> = foodDao.getAllFoods()

    suspend fun delete(food: FoodItem) = foodDao.deleteFood(food)

    suspend fun getFoodsByVendor(vendorId: Long): List<FoodItem> = foodDao.getFoodsByVendor(vendorId)
}