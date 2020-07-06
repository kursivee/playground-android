package com.kursivee.graphql.booking.domain.entity

data class LaunchEntity(
    val id: String,
    val site: String,
    val isBooked: Boolean,
    val mission: MissionEntity,
    val rocket: RocketEntity
)