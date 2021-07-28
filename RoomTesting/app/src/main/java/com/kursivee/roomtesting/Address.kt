package com.kursivee.roomtesting

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Address(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "addressId")
    val addressId: Int,
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "state")
    val state: String
)