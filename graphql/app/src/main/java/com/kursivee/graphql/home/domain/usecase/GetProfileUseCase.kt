package com.kursivee.graphql.home.domain.usecase

import com.kursivee.graphql.base.network.NetworkResponse
import com.kursivee.graphql.home.domain.entity.ProfileEntity
import com.kursivee.graphql.home.domain.repository.MeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProfileUseCase(private val repository: MeRepository) {
    suspend operator fun invoke(): NetworkResponse<ProfileEntity> {
        return withContext(Dispatchers.IO) {
            repository.getProfile()
        }
    }
}