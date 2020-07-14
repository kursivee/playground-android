package com.kursivee.recycler.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kursivee.recycler.databinding.ListItemRightBinding

class ListAdapter(private val list: MutableList<String>): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ListViewHolder(private val binding: ListItemRightBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(s: String) {
            binding.textView2.text = s
        }
    }
}