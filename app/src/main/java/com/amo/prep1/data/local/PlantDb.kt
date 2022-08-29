package com.amo.prep1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amo.prep1.data.local.dao.PlantDao

@Database(entities = [PlantEntity::class] , version = 1)
abstract class PlantDb : RoomDatabase() {
    abstract fun plantDao() : PlantDao
}