package com.kursivee.graphql.booking.data.mapper

import com.heroku.BookTripsMutation
import com.kursivee.graphql.booking.domain.entity.LaunchEntity
import com.kursivee.graphql.booking.domain.entity.MissionEntity
import com.kursivee.graphql.booking.domain.entity.RocketEntity
import com.kursivee.graphql.booking.domain.entity.TripsEntity

fun BookTripsMutation.Mission.toEntity(): MissionEntity {
    return MissionEntity(
        name = requireNotNull(name()),
        imageUri = missionPatch()
    )
}

fun BookTripsMutation.Rocket.toEntity(): RocketEntity {
    return RocketEntity(
        id = id(),
        name = requireNotNull(name()),
        type = requireNotNull(type())
    )
}

fun BookTripsMutation.Launch.toEntity(): LaunchEntity {
    return LaunchEntity(
        id = id(),
        site = requireNotNull(site()),
        isBooked = isBooked,
        mission = requireNotNull(mission()).toEntity(),
        rocket = requireNotNull(rocket()).toEntity()
    )
}

fun BookTripsMutation.BookTrips.toEntity(): TripsEntity {
    return TripsEntity(
        success = success(),
        message = requireNotNull(message()),
        launches = requireNotNull(launches()).map { it.toEntity() }
    )
}