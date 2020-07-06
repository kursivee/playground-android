package com.kursivee.graphql.booking.domain.entity

data class TripsEntity(
    val success: Boolean,
    val message: String,
    val launches: List<LaunchEntity>
)