package com.kursivee.roomtesting

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(entities = [User::class, Address::class], version = 2, autoMigrations = [
    AutoMigration(from = 1, to = 2, spec = Migration::class)
])
abstract class TestDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun addressDao(): AddressDao
}

@RenameTable(fromTableName = "User", toTableName = "NewUserTable")
class Migration: AutoMigrationSpec