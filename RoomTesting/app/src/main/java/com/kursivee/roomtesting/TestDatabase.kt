package com.kursivee.roomtesting

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class TestDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}