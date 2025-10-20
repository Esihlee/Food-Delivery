package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.dao.FoodDAO
import com.example.fooddeliveryapp.data.entity.FoodItem
import com.example.fooddeliveryapp.data.entity.Vendor

class FoodRepository(private val foodDao: FoodDAO) {
    //Food
    suspend fun insert(item: FoodItem) = foodDao.insertFood(item)
    suspend fun getAll(): List<FoodItem> = foodDao.getAllFoods()
    suspend fun deleteFood(food: FoodItem) = foodDao.deleteFood(food)
    suspend fun getFoodsByVendor(vendorId: Long): List<FoodItem> = foodDao.getFoodsByVendor(vendorId)

    //Vendor
    suspend fun getVendorById(id: Long): Vendor = foodDao.getVendorById(id)
    suspend fun updateVendorStatus(id: Long, isOnline: Boolean) = foodDao.updateVendorStatus(id,isOnline)
    suspend fun getAllVendors(): List<Vendor> = foodDao.getAllVendors()
}