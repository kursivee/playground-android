package com.kursivee.graphql.home.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kursivee.graphql.databinding.BookedTripItemBinding
import com.kursivee.graphql.home.domain.entity.BookedTripEntity

class BookedTripsAdapter(
    private val bookedTrips: MutableList<BookedTripEntity> = mutableListOf()
): RecyclerView.Adapter<BookedTripsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookedTripsViewHolder {
        val binding = BookedTripItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookedTripsViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int = bookedTrips.size

    override fun onBindViewHolder(holder: BookedTripsViewHolder, position: Int) {
        holder.bind(bookedTrips[position])
    }

    fun update(bookedTrips: List<BookedTripEntity>) {
        with(this.bookedTrips) {
            clear()
            addAll(bookedTrips)
        }
        notifyDataSetChanged()
    }
}