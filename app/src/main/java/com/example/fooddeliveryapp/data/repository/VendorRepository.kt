package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.dao.VendorDao
import com.example.fooddeliveryapp.data.entity.Vendor
import kotlinx.coroutines.flow.Flow

class VendorRepository(private val vendorDao: VendorDao) {
    suspend fun addVendor(vendor: Vendor) = vendorDao.insert(vendor)
    suspend fun updateVendor(vendor: Vendor) = vendorDao.update(vendor)
    fun getVendorById(id: Long): Flow<Vendor> = vendorDao.getVendorById(id)
    fun getAllVendors(): Flow<List<Vendor>> = vendorDao.getAllVendors()
}