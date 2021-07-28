package com.kursivee.roomtesting

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Insert
    suspend fun insertUsers(user: List<User>)

    @Query("SELECT * FROM NewUserTable")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM NewUserTable WHERE userId = :id")
    suspend fun findUserById(id: Int): User?

    @Delete
    suspend fun deleteUser(user: User)

    @Transaction
    @Query("SELECT * FROM NewUserTable")
    suspend fun getUsersWithAddress(): List<UserWithAddress>
}