package com.kursivee.graphql.home.domain.entity

data class TripsEntity(
    val success: Boolean,
    val message: String,
    val launches: List<LaunchEntity>
)