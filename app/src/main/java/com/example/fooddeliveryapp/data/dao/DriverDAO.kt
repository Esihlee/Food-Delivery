package com.example.fooddeliveryapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fooddeliveryapp.data.entity.Driver

@Dao
interface DriverDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDriver(driver: Driver)

    @Update
    suspend fun updateDriver(driver: Driver)

    @Query("SELECT * FROM drivers")
    suspend fun getAllDrivers(): List<Driver>
}