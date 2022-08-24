package com.amo.prep1.ext

import androidx.test.espresso.IdlingResource
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

class OkhttpIdlingResource private constructor(
    name : String ,
    private val dispatcher: Dispatcher
): IdlingResource {

    private val resourceName = name
    private var callback : IdlingResource.ResourceCallback? = null

    companion object{
        fun create(name : String, client : OkHttpClient) : OkhttpIdlingResource{
            return OkhttpIdlingResource(name , client.dispatcher)
        }
    }

    override fun getName(): String {
        return resourceName
    }

    override fun isIdleNow(): Boolean {
       return dispatcher.runningCallsCount() == 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
       this.callback = callback
    }


}