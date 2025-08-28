package com.example.fooddeliveryapp.data.dao

import androidx.room.*
import com.example.fooddeliveryapp.data.entity.Vendor
import kotlinx.coroutines.flow.Flow

@Dao
interface VendorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vendor: Vendor)

    @Update
    suspend fun update(vendor: Vendor)

    @Query("SELECT * FROM vendors WHERE vendorId = :id")
    fun getVendorById(id: Long): Flow<Vendor>

    @Query("SELECT * FROM vendors")
    fun getAllVendors(): Flow<List<Vendor>>
}