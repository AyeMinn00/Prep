package com.amo.prep1

import com.amo.test_shared.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTestUsingRule {

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    @Test
    fun settingMainDispatcher() = runTest {
        val vm = HomeViewModel()
        vm.loadMessage()
        assertEquals("Greetings!", vm.message.value)
    }

}