package com.kursivee.roomtesting

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Insert
    suspend fun insertUsers(user: List<User>)

    @Query("SELECT * FROM User")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun findUserById(id: Int): User?

    @Delete
    suspend fun deleteUser(user: User)
}