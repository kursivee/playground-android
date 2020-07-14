package com.kursivee.recycler.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.kursivee.recycler.databinding.MainFragmentBinding
import com.kursivee.recycler.ui.main.adapter.HeaderAdapter
import com.kursivee.recycler.ui.main.adapter.ContentAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val headerAdapter: HeaderAdapter = HeaderAdapter()
    private val contentAdapter: ContentAdapter = ContentAdapter()

    private var nullableBinding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding
        get() = nullableBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(binding.rvItems) {
            adapter = ConcatAdapter(headerAdapter, contentAdapter)
            layoutManager = LinearLayoutManager(requireContext())
        }
        contentAdapter.submitList(mutableListOf("What's", "Going", "On"))
    }

    override fun onDestroyView() {
        nullableBinding = null
        super.onDestroyView()
    }
}