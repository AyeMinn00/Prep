package com.amo.prep1.data

import com.amo.prep1.data.remote.ApiService
import com.amo.prep1.model.Plant
import com.amo.prep1.model.Result
import com.amo.prep1.model.Result.Success
import com.amo.prep1.model.Result.Error
import kotlinx.coroutines.delay
import java.lang.Exception

class DefaultDataSource(private val apiService: ApiService) : DataSource {

    override suspend fun getAllPlants(): Result<List<Plant>> {
        delay(1000)
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