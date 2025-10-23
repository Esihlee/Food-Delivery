package com.example.fooddeliveryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.db.AppDatabase
import com.example.fooddeliveryapp.data.entity.FoodItem
import com.example.fooddeliveryapp.data.repository.FoodRepository
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: FoodRepository

    init {
        val dao = AppDatabase.getDatabase(application).foodDao()
        repo = FoodRepository(dao)
    }

    fun insertFood(food: FoodItem) = viewModelScope.launch {
        repo.insert(food)
    }

    fun getAllFoods(onResult: (List<FoodItem>) -> Unit) = viewModelScope.launch {
        val data = repo.getAll()
        onResult(data)
    }

    fun getFoodsByVendor(vendorId: Long, onResult: (List<FoodItem>) -> Unit) = viewModelScope.launch {
        val data = repo.getFoodsByVendor(vendorId)
        onResult(data)
    }

    fun deleteFood(food: FoodItem, onComplete: () -> Unit) = viewModelScope.launch {
        repo.deleteFood(food)
        onComplete()
    }

}
