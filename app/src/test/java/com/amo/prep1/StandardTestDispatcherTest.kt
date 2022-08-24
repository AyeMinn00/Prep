package com.amo.prep1

import com.amo.prep1.data.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class StandardTestDispatcherTest {

    @Test
    fun standardTest() = runTest(UnconfinedTestDispatcher()){
        val repo = UserRepository()

        launch {  repo.register("ko") }
        launch {  repo.register("aye") }
        assertEquals(listOf("ko","aye") , repo.getAllUsers())
    }

}