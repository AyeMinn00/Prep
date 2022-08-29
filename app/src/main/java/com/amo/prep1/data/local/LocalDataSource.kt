package com.amo.prep1.data.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAll(): Flow<List<PlantEntity>>

    suspend fun loadAllByName(plantName: String) : List<PlantEntity>

    suspend fun insertAll(vararg plant : PlantEntity)

    suspend fun delete(plant : PlantEntity)

}