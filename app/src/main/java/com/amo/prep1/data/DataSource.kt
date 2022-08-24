package com.amo.prep1.data

import com.amo.prep1.model.Plant
import com.amo.prep1.model.Result

interface DataSource {

    suspend fun getAllPlants() : Result<List<Plant>>

}