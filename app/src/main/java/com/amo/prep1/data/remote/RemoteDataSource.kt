package com.amo.prep1.data.remote

import com.amo.prep1.model.Plant
import com.amo.prep1.model.Result

interface RemoteDataSource {

    suspend fun getAllPlants() : Result<List<Plant>>

}