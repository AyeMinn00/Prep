package com.amo.prep1.ui.plants

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.amo.prep1.R
import com.amo.prep1.ext.OkhttpIdlingResource
import com.amo.prep1.ext.launchFragmentInHiltContainer
import com.amo.prep1.model.Plant
import com.amo.test_shared.util.MainCoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class PlantsFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var idlingResource: OkhttpIdlingResource

    @Inject
    lateinit var okhttpClient: OkHttpClient

    @Before
    fun setup() {
        hiltRule.inject()
        idlingResource = OkhttpIdlingResource.create("okhttp", okhttpClient)
        IdlingRegistry.getInstance().register(idlingResource)
        mockWebServer = MockWebServer()
    }

    @After
    fun clear() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun assert_show_loading_whenLaunch() {
        launchFragmentInHiltContainer<PlantsFragment> {
            onLoading()
        }
        onView(withId(R.id.loading)).check(matches(isDisplayed()))
        onView(withId(R.id.viewNoConnection)).check(matches(not(isDisplayed())))
        onView(withId(R.id.rcPlants)).check(matches(not(isDisplayed())))
    }

    @Test
    fun assert_hide_loading_whenPlantsShow() {
        launchFragmentInHiltContainer<PlantsFragment> {
            onBindPlants(listOf())
        }
        onView(withId(R.id.loading)).check(matches(not(isDisplayed())))
        onView(withId(R.id.viewNoConnection)).check(matches(not(isDisplayed())))
        onView(withId(R.id.rcPlants)).check(matches((isDisplayed())))
    }

    @Test
    fun assert_show_error() {
        launchFragmentInHiltContainer<PlantsFragment> {
            onError()
        }
        onView(withId(R.id.loading)).check(matches(not(isDisplayed())))
        onView(withId(R.id.viewNoConnection)).check(matches((isDisplayed())))
        onView(withId(R.id.rcPlants)).check(matches(not(isDisplayed())))
    }

    @Test
    fun assert_show_loading_when_perform_retry() {
        launchFragmentInHiltContainer<PlantsFragment> {
            onError()
        }
        onView(withId(R.id.btnRetry)).perform(click())
    }

    @Test
    fun click_plant_navigate_detail_fragment() {
        val plant = Plant("1", "Mongo", "One Diamond")
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInHiltContainer<PlantsFragment> {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
            onBindPlants(listOf(plant))
        }
        onView(withId(R.id.rcPlants))
            .perform(actionOnItemAtPosition<PlantViewHolder>(0, click()))
        assertEquals(navController.currentDestination?.id, R.id.plantDetailFragment)
    }

//    @Test
//    fun show_plant_list_when_api_return_success() {
//        enqueueResponse("plants.json")
//        launchFragmentInHiltContainer<PlantsFragment> { }
//        onView(withId(R.id.loading)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
//        onView(withId(R.id.viewNoConnection)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
//        onView(withId(R.id.rcPlants)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
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