package com.kursivee.graphql.home.data.mapping

import arrow.core.Either
import com.apollographql.apollo.api.Response
import com.heroku.GetMeQuery
import com.kursivee.graphql.base.network.ErrorCode
import com.kursivee.graphql.base.network.ErrorEntity
import com.kursivee.graphql.base.network.NetworkResponse
import com.kursivee.graphql.booking.data.mapper.toEntity
import com.kursivee.graphql.home.domain.entity.BookedTripEntity
import com.kursivee.graphql.home.domain.entity.ProfileEntity

private fun GetMeQuery.Me.toProfileEntity(): ProfileEntity {
    return ProfileEntity(
        email(),
        profileImage()
    )
}

fun Response<GetMeQuery.Data>.toProfileEntity(): NetworkResponse<ProfileEntity> {
    if(!errors.isNullOrEmpty()) return Either.left(ErrorEntity(ErrorCode.GENERIC_ERROR))
    return data?.me()?.toProfileEntity()?.let { entity ->
        Either.right(entity)
    } ?: Either.left(ErrorEntity(ErrorCode.GENERIC_ERROR))
}

private fun GetMeQuery.Me.toBookedTripEntities(): List<BookedTripEntity> {
    return trips().map { trip ->
        with(trip.fragments().launchFragment()) {
            BookedTripEntity(
                requireNotNull(site()),
                requireNotNull(mission()?.fragments()?.missionFragment()?.toEntity()),
                requireNotNull(rocket()?.fragments()?.rocketFragment()?.toEntity())
            )
        }
    }
}

fun Response<GetMeQuery.Data>.toBookedTripEntities(): NetworkResponse<List<BookedTripEntity>> {
    if(!errors.isNullOrEmpty()) return Either.left(ErrorEntity(ErrorCode.GENERIC_ERROR))
    return data?.me()?.toBookedTripEntities()?.let { entities ->
        Either.right(entities)
    } ?: Either.left(ErrorEntity(ErrorCode.GENERIC_ERROR))
}
