package com.kursivee.graphql.base.cache.data

import com.kursivee.graphql.base.cache.domain.SessionRepository
import com.kursivee.graphql.base.cache.domain.UserEntity

class SessionRepositoryImpl(
    private val userDataSource: UserDataSource
): SessionRepository {
    override fun getUserData(): UserEntity {
        return userDataSource.user
    }

    override fun setUserData(userEntity: UserEntity) {
        userDataSource.user = userEntity
    }

    override fun clear() {
        userDataSource.clear()
    }
}