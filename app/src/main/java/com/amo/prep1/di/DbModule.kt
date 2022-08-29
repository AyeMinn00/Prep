package com.amo.prep1.di

import android.content.Context
import androidx.room.Room
import com.amo.prep1.data.local.DefaultLocalDataSource
import com.amo.prep1.data.local.LocalDataSource
import com.amo.prep1.data.local.PlantDb
import com.amo.prep1.data.local.dao.PlantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun providePlantDb(@ApplicationContext context: Context) = Room.databaseBuilder(context , PlantDb::class.java, "plant.db").build()

    @Singleton
    @Provides
    fun providePlantDao(db : PlantDb) = db.plantDao()

}