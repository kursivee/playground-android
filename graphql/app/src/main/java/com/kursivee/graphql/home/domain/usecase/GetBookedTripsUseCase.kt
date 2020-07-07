package com.kursivee.graphql.home.domain.usecase

import com.kursivee.graphql.base.network.NetworkResponse
import com.kursivee.graphql.home.domain.entity.BookedTripEntity
import com.kursivee.graphql.home.domain.repository.MeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetBookedTripsUseCase(private val repository: MeRepository) {
    suspend operator fun invoke(): NetworkResponse<List<BookedTripEntity>> {
        return withContext(Dispatchers.IO) {
            repository.getBookedTrips()
        }
    }
}