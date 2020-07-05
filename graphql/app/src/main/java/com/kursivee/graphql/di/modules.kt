package com.kursivee.graphql.di

import com.kursivee.graphql.home.presentation.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { HomeViewModel() }
}