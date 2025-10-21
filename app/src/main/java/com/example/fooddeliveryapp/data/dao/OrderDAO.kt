package com.example.fooddeliveryapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fooddeliveryapp.data.entity.Order

@Dao
interface OrderDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM orders")
    suspend fun getAllOrders(): List<Order>

    @Query("UPDATE orders SET status = 'Accepted', driverID = :driverID WHERE orderID = :orderID")
    suspend fun acceptOrder(orderID: Int, driverID: Int)

    @Query("UPDATE orders SET status = 'Declined' WHERE orderID= :orderID")
    suspend fun declineOrder(orderID: Int)
}