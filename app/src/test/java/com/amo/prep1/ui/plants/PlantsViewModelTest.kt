package com.amo.prep1.ui.plants

import com.amo.prep1.data.FakeDataSource
import com.amo.prep1.model.Plant
import com.amo.prep1.model.Result
import com.amo.test_shared.util.MainCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PlantsViewModelTest {

    private lateinit var dataSource : FakeDataSource
    private lateinit var viewModel : PlantsViewModel

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    @Before
    fun setupViewModel(){
        dataSource = FakeDataSource()
        viewModel = PlantsViewModel(dataSource)
    }

    @Test
    fun getPlantsSuccess() = runTest{
        dataSource.setHasError(false)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel.getPlants()
        assertEquals(viewModel.plants.value , Result.Loading)
        advanceUntilIdle()
        assertEquals(viewModel.plants.value , Result.Success<List<Plant>>(listOf()))
    }

    @Test
    fun getPlantsError() = runTest{
        dataSource.setHasError(true)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel.getPlants()
        assertEquals(viewModel.plants.value , Result.Loading)
        advanceUntilIdle()
        assertEquals(viewModel.plants.value , Result.Error(""))
    }

}