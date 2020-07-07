package com.kursivee.graphql.di

import com.kursivee.graphql.auth.data.LoginDataSource
import com.kursivee.graphql.auth.data.LoginRepositoryImpl
import com.kursivee.graphql.auth.domain.LoginRepository
import com.kursivee.graphql.auth.domain.LoginUseCase
import com.kursivee.graphql.auth.presentation.LoginViewModel
import com.kursivee.graphql.base.cache.data.SessionRepositoryImpl
import com.kursivee.graphql.base.cache.data.UserDataSource
import com.kursivee.graphql.base.cache.domain.ClearSessionUseCase
import com.kursivee.graphql.base.cache.domain.GetUserUseCase
import com.kursivee.graphql.base.cache.domain.SessionRepository
import com.kursivee.graphql.base.cache.domain.SetUserUseCase
import com.kursivee.graphql.base.koin.Scopes
import com.kursivee.graphql.base.network.ApolloClientFactory
import com.kursivee.graphql.base.network.OkHttpClientFactory
import com.kursivee.graphql.booking.data.TripsDataSource
import com.kursivee.graphql.booking.data.TripsInMemDataSource
import com.kursivee.graphql.booking.data.TripsRepositoryImpl
import com.kursivee.graphql.booking.domain.BookTripsUseCase
import com.kursivee.graphql.booking.domain.ObserveTripCountUseCase
import com.kursivee.graphql.booking.domain.SubscribeTripCountUseCase
import com.kursivee.graphql.booking.domain.TripsRepository
import com.kursivee.graphql.booking.presentation.BookingViewModel
import com.kursivee.graphql.home.data.datasource.MeDataSource
import com.kursivee.graphql.home.data.repository.MeRepositoryImpl
import com.kursivee.graphql.home.domain.repository.MeRepository
import com.kursivee.graphql.home.domain.usecase.GetBookedTripsUseCase
import com.kursivee.graphql.home.presentation.HomeViewModel
import com.kursivee.graphql.main.presentation.SessionViewModel
import com.kursivee.graphql.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val appModules = module {
    scope(named(Scopes.SESSION_SCOPE)) {
        viewModel { BookingViewModel() }
        viewModel { SessionViewModel(get(), get()) }
        viewModel { MainViewModel(get(), get(), get()) }
        scoped { SubscribeTripCountUseCase(get()) }
        scoped<TripsRepository> { TripsRepositoryImpl(get(), get()) }
        scoped { TripsInMemDataSource() }
        scoped { TripsDataSource(get()) }
        scoped { BookTripsUseCase(get()) }
        scoped { ObserveTripCountUseCase(get()) }

        // Home
        scoped { GetBookedTripsUseCase(get()) }
        scoped { MeRepositoryImpl(get()) as MeRepository }
        scoped { MeDataSource(get()) }
        viewModel { HomeViewModel(get()) }

    }

    viewModel { LoginViewModel(get()) }
    single { LoginUseCase(get(), get()) }
    single { LoginDataSource(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
}

val baseModules = module {
    single<SessionRepository> { SessionRepositoryImpl(get()) }
    single { GetUserUseCase(get()) }
    single { ClearSessionUseCase(get()) }
    single { UserDataSource() }
    single { SetUserUseCase(get()) }
    single { OkHttpClientFactory(get()).get() }
    single { ApolloClientFactory(get()).get() }
}