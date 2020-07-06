package com.kursivee.graphql.booking.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kursivee.graphql.R
import org.koin.android.viewmodel.ext.android.viewModel

class BookingFragment : Fragment() {

    companion object {
        fun newInstance() = BookingFragment()
    }

    val viewModel: BookingViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.booking_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}