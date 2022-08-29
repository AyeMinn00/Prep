package com.amo.prep1.di

import com.amo.prep1.data.DefaultRepository
import com.amo.prep1.data.local.DefaultLocalDataSource
import com.amo.prep1.data.local.LocalDataSource
import com.amo.prep1.data.local.dao.PlantDao
import com.amo.prep1.data.remote.ApiService
import com.amo.prep1.data.remote.DefaultRemoteDataSource
import com.amo.prep1.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepo(localDataSource: LocalDataSource , remoteDataSource: RemoteDataSource)  = DefaultRepository(localDataSource , remoteDataSource)

    @Singleton
    @Provides
    fun provideLocalDataSource(dao : PlantDao): LocalDataSource = DefaultLocalDataSource(dao)

    @Singleton
    @Provides
    fun provideRemoteDataSource(api : ApiService): RemoteDataSource = DefaultRemoteDataSource(api)


}