package com.pluu.sample.codeforreadability.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pluu.sample.codeforreadability.databinding.ItemSampleBinding
import com.pluu.sample.codeforreadability.model.SampleItem

class SampleAdapter : RecyclerView.Adapter<SampleViewHolder>() {

    private var list = mutableListOf<SampleItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun submitList(item: List<SampleItem>) {
        list.clear()
        list.addAll(item)
        notifyDataSetChanged()
    }
}