package com.amo.prep1.ui.plants

import com.amo.prep1.data.DataSource
import com.amo.prep1.model.Plant
import com.amo.prep1.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import com.amo.prep1.model.Result.Success
import com.amo.test_shared.util.MainCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class PlantsViewModelMockTest {

    private val datasource = mock(DataSource::class.java)
    private lateinit var viewModel  : PlantsViewModel

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        viewModel = PlantsViewModel(datasource)
    }

    @Test
    fun getPlantsSuccess() = runTest {
        val response = Success<List<Plant>>(listOf())
        `when`(datasource.getAllPlants()).thenReturn(response)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel.getPlants()
        Assert.assertEquals(viewModel.plants.value, Result.Loading)
        advanceUntilIdle()
        Assert.assertEquals(viewModel.plants.value, response)
    }

    @Test
    fun getPlantsFail() = runTest {
        val response = Result.Error("")
        `when`(datasource.getAllPlants()).thenReturn(response)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel.getPlants()
        Assert.assertEquals(viewModel.plants.value , Result.Loading)
        advanceUntilIdle()
        Assert.assertEquals(viewModel.plants.value , response)
    }


}