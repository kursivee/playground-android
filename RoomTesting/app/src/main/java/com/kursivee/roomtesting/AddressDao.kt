package com.kursivee.roomtesting

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AddressDao {
    @Insert
    suspend fun insertAddress(address: Address)

    @Insert
    suspend fun insertAddresses(addresses: List<Address>)

    @Query("SELECT * FROM Address")
    suspend fun getAddresses(): List<Address>

    @Query("SELECT * FROM Address WHERE addressId = :id")
    suspend fun findAddressById(id: Int): Address?

    @Delete
    suspend fun deleteAddress(address: Address)
}