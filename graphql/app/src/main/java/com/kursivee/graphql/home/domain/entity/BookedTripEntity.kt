package com.kursivee.graphql.home.domain.entity

import com.kursivee.graphql.booking.domain.entity.MissionEntity
import com.kursivee.graphql.booking.domain.entity.RocketEntity

data class BookedTripEntity(
    val site: String,
    val mission: MissionEntity,
    val rocket: RocketEntity
)