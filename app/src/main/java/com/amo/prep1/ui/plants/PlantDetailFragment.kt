package com.amo.prep1.ui.plants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.amo.prep1.R
import com.amo.prep1.databinding.FragmentPlantDetailBinding


class PlantDetailFragment : Fragment() {

    private val args : PlantDetailFragmentArgs by navArgs()
    private lateinit var binding : FragmentPlantDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantDetailBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPlantName()
    }

    private fun setPlantName(){
        binding.plantName.text = args.plant
    }


}