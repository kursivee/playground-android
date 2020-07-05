package com.kursivee.graphql.base.cache.data

import com.kursivee.graphql.base.cache.domain.UserEntity

class UserDataSource {
    var user: UserEntity = UserEntity()

    fun clear() {
        user = UserEntity()
    }
}