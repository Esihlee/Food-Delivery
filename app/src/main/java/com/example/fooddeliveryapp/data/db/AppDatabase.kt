package com.example.fooddeliveryapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fooddeliveryapp.data.dao.FoodDAO
import com.example.fooddeliveryapp.data.dao.UserDAO
import com.example.fooddeliveryapp.data.entity.FoodItem
import com.example.fooddeliveryapp.data.entity.User

@Database(entities = [User::class, FoodItem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun foodDao(): FoodDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "food_delivery_db"
                )
                    .fallbackToDestructiveMigration()  // TEMPORARY: clears old DB on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
