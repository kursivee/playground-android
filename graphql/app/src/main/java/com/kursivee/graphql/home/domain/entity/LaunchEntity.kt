package com.kursivee.graphql.home.domain.entity

data class LaunchEntity(
    val id: String,
    val site: String,
    val isBooked: Boolean,
    val mission: MissionEntity,
    val rocket: RocketEntity
)