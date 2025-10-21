package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.dao.DriverDAO
import com.example.fooddeliveryapp.data.entity.Driver

class DriverRepository (private val driverDao: DriverDAO){

    suspend fun addDriver(driver: Driver){
        driverDao.insertDriver(driver)
    }

    suspend fun updateDriver(driver: Driver){
        driverDao.updateDriver(driver)
    }

    suspend fun getAll() = driverDao.getAllDrivers()
}