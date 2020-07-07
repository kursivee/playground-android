package com.kursivee.graphql.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.extensions.either.foldable.fold
import arrow.core.extensions.either.foldable.get
import arrow.core.getOrElse
import com.kursivee.graphql.home.domain.entity.BookedTripEntity
import com.kursivee.graphql.home.domain.usecase.GetBookedTripsUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getBookedTripsUseCase: GetBookedTripsUseCase
) : ViewModel() {

    private val mutableBookedTrips: MutableLiveData<List<BookedTripEntity>> = MutableLiveData(listOf())
    val bookedTrips: LiveData<List<BookedTripEntity>> = mutableBookedTrips

    fun getBookedTrips() {
        viewModelScope.launch {
            getBookedTripsUseCase().fold(ifLeft = {}, ifRight = ::updateBookedTrips)
        }
    }

    private fun updateBookedTrips(bookedTrips: List<BookedTripEntity>) {
        this.mutableBookedTrips.value = bookedTrips
    }
}