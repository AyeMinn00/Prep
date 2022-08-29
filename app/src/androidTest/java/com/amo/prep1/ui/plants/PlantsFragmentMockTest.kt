package com.amo.prep1.ui.plants

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.amo.prep1.R
import com.amo.prep1.ext.OkhttpIdlingResource
import com.amo.prep1.ext.launchFragmentInHiltContainer
import com.amo.prep1.model.Result
import com.amo.prep1.util.ViewModelUtil
import com.amo.test_shared.util.MainCoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltAndroidTest
class PlantsFragmentMockTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var mockWebServer: MockWebServer

    private val plants = MutableStateFlow(Result.Loading)

    @Before
    fun setup() {
        hiltRule.inject()
        mockWebServer = MockWebServer()
    }

    @After
    fun clear() {
        mockWebServer.shutdown()
    }

//    @Test
//    fun show_plant_list_when_api_return_success() {
//        enqueueResponse("plants.json")
//        launchFragmentInHiltContainer<PlantsFragment> {
//        }
//        onView(ViewMatchers.withId(R.id.loading))
//            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
//        onView(ViewMatchers.withId(R.id.viewNoConnection))
//            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
//        onView(ViewMatchers.withId(R.id.rcPlants))
//            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
//    }

    private fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val mockResponse = MockResponse()
        val source = inputStream.source().buffer()
        mockWebServer.enqueue(
            mockResponse.setBody(source.readString(Charsets.UTF_8))
        )
    }


}