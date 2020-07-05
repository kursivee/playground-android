package com.kursivee.graphql.base.cache.domain

interface SessionRepository {
    fun getUserData(): UserEntity
    fun setUserData(userEntity: UserEntity)
    fun clear()
}