package com.kursivee.roomtesting

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithAddress(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val address: Address
)