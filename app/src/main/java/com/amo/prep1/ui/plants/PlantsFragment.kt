package com.amo.prep1.ui.plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amo.prep1.model.Result
import dagger.hilt.android.AndroidEntryPoint
import  com.amo.prep1.databinding.FragmentPlantsBinding
import com.amo.prep1.model.Plant
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantsFragment : Fragment() {

    private var binding: FragmentPlantsBinding? = null
    private val viewModel: PlantsViewModel by viewModels()
    private var adapter: PlantsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configViews()
        observePlants()
    }

    override fun onDestroyView() {
        adapter = null
        binding = null
        super.onDestroyView()
    }

    private fun configViews() {
        initRecyclerView()
        configRetryButton()
    }

    private fun initRecyclerView() {
        adapter = PlantsAdapter {
            onPlantClick(it)
        }
        binding!!.rcPlants.adapter = adapter
        binding!!.rcPlants.layoutManager = LinearLayoutManager(this@PlantsFragment.context)
    }

    private fun configRetryButton() {
        binding?.btnRetry?.setOnClickListener {
            retry()
        }
    }

    private fun onPlantClick(plant: Plant?) {
        val action =
            PlantsFragmentDirections.actionPlantsFragmentToPlantDetailFragment(plant?.name ?: "")
        findNavController().navigate(action)
    }

    private fun observePlants() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.plants.collect {
                    when (it) {
                        is Result.Success -> {
                            onBindPlants(it.data)
                        }
                        is Result.Error -> {
                            onError()
                        }
                        is Result.Loading -> {
                            onLoading()
                        }
                    }
                }
            }
        }
    }

    internal fun onBindPlants(data: List<Plant>) {
        adapter?.submitList(data)
        binding?.loading?.visibility = View.INVISIBLE
        binding?.viewNoConnection?.visibility = View.INVISIBLE
        binding?.rcPlants?.visibility = View.VISIBLE
    }

    internal fun onError() {
        binding?.loading?.visibility = View.INVISIBLE
        binding?.viewNoConnection?.visibility = View.VISIBLE
        binding?.rcPlants?.visibility = View.INVISIBLE
    }

    internal fun onLoading() {
        binding?.loading?.visibility = View.VISIBLE
        binding?.viewNoConnection?.visibility = View.INVISIBLE
        binding?.rcPlants?.visibility = View.INVISIBLE
    }

    private fun retry() {
        viewModel.getPlants()
    }

}