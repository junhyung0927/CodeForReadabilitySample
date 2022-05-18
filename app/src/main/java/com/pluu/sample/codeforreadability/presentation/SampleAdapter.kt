package com.pluu.sample.codeforreadability.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pluu.sample.codeforreadability.model.SampleItem

class SampleAdapter(
    private val onFavorite: (String) -> Unit
) : RecyclerView.Adapter<SampleViewHolder>() {

    private var list = mutableListOf<SampleItem>()

    private var favoriteText: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder.create(parent, onFavorite)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.onBind(list[position], favoriteText.orEmpty())
    }

    override fun getItemCount(): Int = list.size

    fun submitList(item: List<SampleItem>) {
        list.clear()
        list.addAll(item)
        notifyDataSetChanged()
    }

    fun updateFavorite(text: String) {
        this.favoriteText = text
    }
}