package com.amo.prep1.data.remote

import com.amo.prep1.model.Plant
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("googlecodelabs/kotlin-coroutines/master/advanced-coroutines-codelab/sunflower/src/main/assets/plants.json")
    suspend fun getAllPlants(): Response<List<Plant>>

}