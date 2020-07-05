package com.kursivee.graphql.di

import com.kursivee.graphql.auth.data.LoginDataSource
import com.kursivee.graphql.auth.data.LoginRepositoryImpl
import com.kursivee.graphql.auth.domain.LoginRepository
import com.kursivee.graphql.auth.domain.LoginUseCase
import com.kursivee.graphql.base.cache.data.SessionRepositoryImpl
import com.kursivee.graphql.base.cache.data.UserDataSource
import com.kursivee.graphql.base.cache.domain.ClearSessionUseCase
import com.kursivee.graphql.base.cache.domain.GetUserUseCase
import com.kursivee.graphql.base.cache.domain.SessionRepository
import com.kursivee.graphql.base.cache.domain.SetUserUseCase
import com.kursivee.graphql.base.network.ApolloClientFactory
import com.kursivee.graphql.base.network.OkHttpClientFactory
import com.kursivee.graphql.home.data.TripsDataSource
import com.kursivee.graphql.home.data.TripsInMemDataSource
import com.kursivee.graphql.home.data.TripsRepositoryImpl
import com.kursivee.graphql.home.domain.BookTripsUseCase
import com.kursivee.graphql.home.domain.SubscribeTripCountUseCase
import com.kursivee.graphql.home.domain.TripsRepository
import com.kursivee.graphql.home.presentation.HomeViewModel
import com.kursivee.graphql.main.presentation.SessionViewModel
import com.kursivee.graphql.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val appModules = module {
    viewModel { HomeViewModel() }
    viewModel { SessionViewModel(get(), get()) }
    viewModel { MainViewModel(get(), get()) }
    single { SubscribeTripCountUseCase(get()) }
    single<TripsRepository> { TripsRepositoryImpl(get(), get()) }
    single { TripsInMemDataSource() }
    single { TripsDataSource(get()) }
    single { LoginUseCase(get(), get()) }
    single { LoginDataSource(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single { BookTripsUseCase(get()) }
}

val baseModules = module {
    single<SessionRepository> { SessionRepositoryImpl(get()) }
    single { UserDataSource() }
    single { GetUserUseCase(get()) }
    single { SetUserUseCase(get()) }
    single { ClearSessionUseCase(get()) }
    single { OkHttpClientFactory(get()).get() }
    single { ApolloClientFactory(get()).get() }
}