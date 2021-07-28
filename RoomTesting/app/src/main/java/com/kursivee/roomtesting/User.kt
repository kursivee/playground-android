package com.kursivee.roomtesting

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "NewUserTable"
)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "name")
    val name: String
)