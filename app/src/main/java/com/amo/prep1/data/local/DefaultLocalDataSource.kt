package com.amo.prep1.data.local

import com.amo.prep1.data.local.dao.PlantDao
import kotlinx.coroutines.flow.Flow

class DefaultLocalDataSource(
    private val plantDao : PlantDao
) : LocalDataSource {

    override fun getAll(): Flow<List<PlantEntity>> {
        return plantDao.getAll()
    }

    override suspend fun loadAllByName(plantName: String): List<PlantEntity> {
       return plantDao.loadAllByName(plantName)
    }

    override suspend fun insertAll(vararg plant: PlantEntity) {
        plantDao.insertAll(*plant)
    }

    override suspend fun delete(plant: PlantEntity) {
        plantDao.delete(plant)
    }


}