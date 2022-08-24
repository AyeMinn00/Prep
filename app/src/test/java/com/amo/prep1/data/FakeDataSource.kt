package com.amo.prep1.data

import com.amo.prep1.model.Plant
import com.amo.prep1.model.Result

class FakeDataSource(private var hasError: Boolean = false ) : DataSource {

    fun setHasError(error: Boolean) {
        hasError = error
    }

    override suspend fun getAllPlants(): Result<List<Plant>> {
        if (hasError)
            return Result.Error("")
        return Result.Success(listOf())
    }


}