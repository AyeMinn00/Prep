package com.amo.prep1.model


sealed class Result<out R> {

    data class Success<out T>(val data : T) : Result<T>()

    data class Error(val error : String) : Result<Nothing>()

    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            Loading -> "Loading"
        }
    }

}