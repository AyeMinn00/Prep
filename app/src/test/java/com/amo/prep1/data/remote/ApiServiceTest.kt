package com.amo.prep1.data.remote

import com.google.gson.stream.MalformedJsonException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class ApiServiceTest {

    private lateinit var apiService: ApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun clear(){
        mockWebServer.shutdown()
    }

    @Test
    fun success() = runTest{
        enqueueResponse("plants.json")
        val responsePlants = apiService.getAllPlants()
        val request = mockWebServer.takeRequest()
        assertEquals(request.path , "/googlecodelabs/kotlin-coroutines/master/advanced-coroutines-codelab/sunflower/src/main/assets/plants.json")
        assertNotNull(responsePlants)
        assertNotNull(responsePlants.body())
        assertEquals(responsePlants.body()?.size , 2)
    }


    @Test(expected = MalformedJsonException::class)
    fun malformedJson() = runTest{
        enqueueResponse("plants-malform.json")
        apiService.getAllPlants()
    }

    private fun enqueueResponse(fileName : String){
        val inputStream  = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val mockResponse = MockResponse()
        val source = inputStream.source().buffer()
        mockWebServer.enqueue(
            mockResponse.setBody(source.readString(Charsets.UTF_8))
        )
    }

}