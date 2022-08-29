package com.amo.prep1.data

import com.amo.prep1.data.local.LocalDataSource
import com.amo.prep1.data.local.PlantEntity
import com.amo.prep1.data.local.asDomainModel
import com.amo.prep1.data.remote.RemoteDataSource
import com.amo.prep1.model.Plant
import com.amo.prep1.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Singleton

class DefaultRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    init {
        Timber.e("initialized...")
    }

    val plants : Flow<List<Plant>>  = localDataSource.getAll().map {
        it.asDomainModel()
    }

    override suspend fun getAllPlants() {
        Timber.e("getAllPlants()")
        when( val plants = remoteDataSource.getAllPlants()){
            is Result.Success -> {
                val entities = plants.data.map { PlantEntity(id = it.plantId , name = it.name , description = it.description) }
                localDataSource.insertAll(*entities.toTypedArray())
            }
            else -> {}
        }
    }

}