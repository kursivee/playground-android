package com.kursivee.graphql.base.network

import arrow.core.Either

typealias NetworkResponse<T> = Either<ErrorEntity, T>