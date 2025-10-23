package com.example.fooddeliveryapp.data.dao

import androidx.room.*
import com.example.fooddeliveryapp.data.entity.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1") // ✅ matches entity
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1") // ✅ matches entity
    suspend fun validateUser(email: String, password: String): User?
}
