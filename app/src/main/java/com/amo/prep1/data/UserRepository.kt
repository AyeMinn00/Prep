package com.amo.prep1.data

import kotlinx.coroutines.delay

class UserRepository {

    private val users = mutableListOf<String>()

    suspend fun register(name : String){
        delay(100L)
        users += name
    }

    suspend fun getAllUsers() : List<String>{
        delay(1000L)
        return users
    }


}