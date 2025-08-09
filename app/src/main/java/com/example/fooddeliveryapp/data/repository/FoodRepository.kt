package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.dao.FoodDAO
import com.example.fooddeliveryapp.data.entity.FoodItem

class FoodRepository(private val foodDao: FoodDAO) {
    suspend fun insert(item: FoodItem) = foodDao.insertFood(item)
    suspend fun getAll() = foodDao.getAllFoods()
}