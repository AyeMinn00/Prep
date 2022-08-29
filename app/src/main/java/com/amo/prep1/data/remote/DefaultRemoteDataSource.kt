package com.amo.prep1.data.remote

import com.amo.prep1.model.Plant
import com.amo.prep1.model.Result
import com.amo.prep1.model.Result.Success
import com.amo.prep1.model.Result.Error
import kotlinx.coroutines.delay
import timber.log.Timber
import java.lang.Exception

class DefaultRemoteDataSource(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getAllPlants(): Result<List<Plant>> {
        Timber.e("getAllPlants invoke")
        delay(3000)
        try {
            val response = apiService.getAllPlants()
            if (response.isSuccessful) {
                val body = response.body() ?: return Error("Empty Body")
                return Success(body)
            }
            return Error("error ${response.code()} , ${response.message()}")
        } catch (e: Exception) {
            return Error(e.message ?: "Unknown error")
        }
    }

}