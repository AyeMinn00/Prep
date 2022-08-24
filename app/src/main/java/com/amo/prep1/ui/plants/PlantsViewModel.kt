package com.amo.prep1.ui.plants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amo.prep1.data.DataSource
import com.amo.prep1.model.Plant
import com.amo.prep1.model.Result
import com.amo.prep1.model.Result.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PlantsViewModel @Inject constructor(
    private val dataSource: DataSource
) : ViewModel() {

    private val _plants = MutableStateFlow<Result<List<Plant>>>(Loading)
    val plants: StateFlow<Result<List<Plant>>> = _plants

    init {
        getPlants()
    }


    fun getPlants() {
        _plants.update {
            Loading
        }
        viewModelScope.launch {
            _plants.value = dataSource.getAllPlants()
        }
    }

}