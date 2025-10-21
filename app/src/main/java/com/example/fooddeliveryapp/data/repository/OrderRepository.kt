package com.example.fooddeliveryapp.data.repository

import com.example.fooddeliveryapp.data.dao.DriverDAO
import com.example.fooddeliveryapp.data.dao.OrderDAO
import com.example.fooddeliveryapp.data.entity.Order

class OrderRepository(private val orderDao: OrderDAO,
                      private val driverDao: DriverDAO
) {
    suspend fun placeOrder(order: Order){
        orderDao.insertOrder(order)
    }

    suspend fun acceptOrder(orderID:Int, driverID:Int){
        orderDao.acceptOrder(orderID,driverID)
    }

    suspend fun declineOrder(orderID:Int){
        orderDao.declineOrder(orderID)

    }

    suspend fun getAll() = orderDao.getAllOrders()
}