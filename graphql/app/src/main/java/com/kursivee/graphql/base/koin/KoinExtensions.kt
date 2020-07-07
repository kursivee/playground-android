package com.kursivee.graphql.base.koin

import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

val Koin.sessionScope: Scope
    get() = getOrCreateScope(
        Scopes.SESSION_SCOPE.name,
        named(Scopes.SESSION_SCOPE)
    )