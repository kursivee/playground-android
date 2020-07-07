package com.kursivee.graphql.home.presentation.list

import androidx.recyclerview.widget.RecyclerView
import com.kursivee.graphql.databinding.BookedTripItemBinding
import com.kursivee.graphql.home.domain.entity.BookedTripEntity

class BookedTripsViewHolder(private val binding: BookedTripItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(bookedTripEntity: BookedTripEntity) {
        with(binding) {
            tvMissionName.text = bookedTripEntity.mission.name
            tvRocketName.text = bookedTripEntity.rocket.name
            tvSite.text = bookedTripEntity.site
        }
    }
}