package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.dao.FoodDAO
import com.example.fooddeliveryapp.data.entity.FoodItem
import com.example.fooddeliveryapp.data.entity.Vendor

class FoodRepository(private val foodDao: FoodDAO) {
    //Food
    suspend fun insert(item: foodItem) = foodDao.insertFood(foodItem)
    suspend fun getAll() = foodDao.getAll()

    //Vendor
    suspend fun getItemsByVendor(vendorId: Long) = foodDao.getItemsByVendor(vendorId)