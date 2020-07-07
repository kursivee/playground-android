package com.kursivee.graphql.booking.data.mapper

import com.heroku.BookTripsMutation
import com.heroku.fragment.LaunchFragment
import com.heroku.fragment.MissionFragment
import com.heroku.fragment.RocketFragment
import com.kursivee.graphql.booking.domain.entity.LaunchEntity
import com.kursivee.graphql.booking.domain.entity.MissionEntity
import com.kursivee.graphql.booking.domain.entity.RocketEntity
import com.kursivee.graphql.booking.domain.entity.TripsEntity


fun MissionFragment.toEntity(): MissionEntity {
    return MissionEntity(
        name = requireNotNull(name()),
        imageUri = missionPatch()
    )
}

fun RocketFragment.toEntity(): RocketEntity {
    return RocketEntity(
        id = id(),
        name = requireNotNull(name()),
        type = requireNotNull(type())
    )
}

fun LaunchFragment.toEntity(): LaunchEntity {
    return LaunchEntity(
        id = id(),
        site = requireNotNull(site()),
        isBooked = isBooked,
        mission = requireNotNull(mission()?.fragments()?.missionFragment()).toEntity(),
        rocket = requireNotNull(rocket()?.fragments()?.rocketFragment()).toEntity()
    )
}

fun BookTripsMutation.BookTrips.toEntity(): TripsEntity {
    return TripsEntity(
        success = success(),
        message = requireNotNull(message()),
        launches = requireNotNull(launches()?.map { it.fragments().launchFragment() }).map { it.toEntity() }
    )
}