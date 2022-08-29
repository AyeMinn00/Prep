package com.amo.prep1.data.local.dao

import androidx.room.*
import com.amo.prep1.data.local.PlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PlantDao {

    @Query("SELECT * FROM plants")
    abstract fun getAll(): Flow<List<PlantEntity>>

    @Query("SELECT * FROM plants WHERE name IN (:plantName)")
    abstract suspend fun loadAllByName(plantName: String): List<PlantEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertAll(vararg plant: PlantEntity)

    @Delete
    abstract suspend fun delete(plant: PlantEntity)

}