package com.jo.switchgate.core.navigation.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.jo.switchgate.core.navigation.FeatureEntry
import com.jo.switchgate.core.navigation.ktx.navigationContainer
import kotlinx.coroutines.launch

interface NavigationContainer {

    fun <T : FeatureEntry<*, *>> getEntryPoint(cls: Class<T>): T
}

inline fun <reified T : FeatureEntry<*, *>> NavigationContainer.getEntryPoint(): T {
    return getEntryPoint(T::class.java)
}

inline fun <reified T : FeatureEntry<*, *>> Fragment.getEntryPoint(crossinline block: suspend T.() -> Unit) {
    lifecycleScope.launch {
        val entryPoint: T = navigationContainer().getEntryPoint()
        block(entryPoint)
    }
}

inline fun <reified T : FeatureEntry<*, *>> FragmentActivity.getEntryPoint(crossinline block: suspend T.() -> Unit) {
    lifecycleScope.launch {
        val entryPoint: T = navigationContainer().getEntryPoint()
        block(entryPoint)
    }
}
