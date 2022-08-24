package com.amo.prep1.ext

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions
import com.amo.prep1.HillTestActivity

inline fun <reified  T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs : Bundle? = null,
    @StyleRes themeResId: Int = androidx.fragment.testing.R.style.FragmentScenarioEmptyFragmentActivityTheme,
    fragmentFactory: FragmentFactory? = null,
    crossinline action: T.() -> Unit = {}
){
    val startActivity = Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            HillTestActivity::class.java
        )
    ).putExtra(
        "androidx.fragment.app.testing.FragmentScenario" +
                ".EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY",
        themeResId
    )

    ActivityScenario.launch<HillTestActivity>(startActivity).onActivity { activity ->
        fragmentFactory?.let{
            activity.supportFragmentManager.fragmentFactory = it
        }
        val frag  : Fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            Preconditions.checkNotNull(T::class.java.classLoader),
            T::class.java.name
        )
        frag.arguments = fragmentArgs
        activity.supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, frag)
            .commitNow()

        (frag as T).action()
    }

}