package com.kursivee.graphql.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kursivee.graphql.R
import com.kursivee.graphql.base.koin.sessionScope
import com.kursivee.graphql.databinding.HomeFragmentBinding
import com.kursivee.graphql.home.presentation.list.BookedTripsAdapter
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.scope.viewModel

class HomeFragment : Fragment() {

    private var nullableBinding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding
        get() = nullableBinding!!

    private val scope = getKoin().sessionScope
    private val vm: HomeViewModel by scope.viewModel(this)
    private val bookedTripsAdapter: BookedTripsAdapter by lazy {
        BookedTripsAdapter()
    }

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            scope.close()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvBookedTrips.init()
        vm.getBookedTrips()
        vm.bookedTrips.observe(viewLifecycleOwner, Observer { bookedTrips ->
            bookedTripsAdapter.update(bookedTrips)
        })
    }

    private fun RecyclerView.init() {
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        layoutManager = LinearLayoutManager(context)
        adapter = bookedTripsAdapter
    }

    override fun onDestroyView() {
        nullableBinding = null
        super.onDestroyView()
    }
}