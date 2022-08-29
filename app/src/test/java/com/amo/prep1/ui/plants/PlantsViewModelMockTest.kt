package com.amo.prep1.ui.plants

import com.amo.prep1.data.remote.RemoteDataSource
import com.amo.prep1.model.Plant
import com.amo.prep1.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.amo.prep1.model.Result.Success
import com.amo.test_shared.util.MainCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class PlantsViewModelMockTest {

//    private val datasource = mock(DataSource::class.java)
//    private lateinit var viewModel  : PlantsViewModel

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

//    @BeforeEach
//    fun setup(){
//        viewModel = PlantsViewModel(datasource)
//    }

    @Test
    fun getPlantsSuccess() = runTest {
        val datasource = mock(RemoteDataSource::class.java)
        val viewModel = PlantsViewModel(datasource)
        val response = Success<List<Plant>>(listOf())
        `when`(datasource.getAllPlants()).thenReturn(response)
        Dispatchers.setMain(StandardTestDispatcher())
        Assert.assertEquals(viewModel.plants.value, Result.Loading)
        advanceUntilIdle()
        verify(datasource).getAllPlants()
        Assert.assertEquals(viewModel.plants.value, response)
    }

    @Test
    fun getPlantsFail() = runTest {
        val datasource = mock(RemoteDataSource::class.java)
        val viewModel = PlantsViewModel(datasource)
        val response = Result.Error("")
        `when`(datasource.getAllPlants()).thenReturn(response)
        Dispatchers.setMain(StandardTestDispatcher())
        Assert.assertEquals(viewModel.plants.value , Result.Loading)
        advanceUntilIdle()
        verify(datasource).getAllPlants()
        Assert.assertEquals(viewModel.plants.value , response)
    }


}