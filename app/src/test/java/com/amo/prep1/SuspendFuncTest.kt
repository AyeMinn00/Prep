package com.amo.prep1

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class SuspendFuncTest {

    private suspend fun fetchData(): String {
        return "kokoaye"
    }

    @Test
    fun dataShouldBeKoKoAye() = runTest {
        val data = fetchData()
        assertEquals("kokoaye",data)
    }

    @Test
    fun throwError() = runTest {
        val data = fetchData()
        assertNotEquals("hihi", data)
    }

}