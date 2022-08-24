package com.amo.prep1.ui.plants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amo.prep1.databinding.ItemPlantBinding
import com.amo.prep1.model.Plant


class PlantViewHolder(private val binding : ItemPlantBinding) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(item: Plant , onPlantClick : (plant : Plant?) -> Unit){
        binding.plantName.text = item.name
        binding.root.setOnClickListener { onPlantClick(item) }
    }

    companion object{
        fun create(parent : ViewGroup ) : PlantViewHolder{
            val binding = ItemPlantBinding.inflate(LayoutInflater.from(parent.context) ,  parent, false)
            return PlantViewHolder(binding)
        }
    }

}