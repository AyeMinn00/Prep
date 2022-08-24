package com.amo.prep1.ui.plants

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.amo.prep1.model.Plant

class PlantsAdapter(
    private val onPlantClick: (plant: Plant?) -> Unit
) : ListAdapter<Plant, PlantViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position), onPlantClick)
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<Plant>() {
            override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem.plantId == newItem.plantId
            }

            override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem == newItem
            }

        }
    }

}