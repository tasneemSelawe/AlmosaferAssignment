package com.task.almosaferassignment.core.navigation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.clearFragmentResult
import androidx.fragment.app.clearFragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import androidx.navigation.ui.R as NavigationR

interface FeatureEntry<A, R> {

    val destination: Int

    val addAnimation: Boolean get() = true

    fun Fragment.navigate(arguments: A? = null) {
        if (arguments == null) {
            findNavController().navigate(destination, bundleOf(), getNavigationOptions())
        } else {
            findNavController().navigate(destination, bundleOf("arguments" to arguments), getNavigationOptions())
        }
    }

    fun FragmentActivity.navigate(navController: NavController, arguments: A? = null) {
        if (arguments == null) {
            navController.navigate(destination, bundleOf(), getNavigationOptions())
        } else {
            navController.navigate(destination, bundleOf("arguments" to arguments), getNavigationOptions())
        }
    }

    private fun getNavigationOptions(): NavOptions? {
        if (!addAnimation) {
            return null
        }
        return NavOptions.Builder()
            .setEnterAnim(NavigationR.anim.nav_default_enter_anim)
            .setExitAnim(NavigationR.anim.nav_default_exit_anim)
            .setPopEnterAnim(NavigationR.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(NavigationR.anim.nav_default_pop_exit_anim)
            .build()
    }

    fun Fragment.navigateUp() {
        findNavController().popBackStack(destination, false)
    }

    suspend fun Fragment.navigateForResult(arguments: A? = null): R {
        return suspendCancellableCoroutine { cont ->
            navigate(arguments)
            val key = when (val destination = findNavController().currentBackStackEntry?.destination) {
                is FragmentNavigator.Destination -> {
                    destination.className
                }
                is DialogFragmentNavigator.Destination -> {
                    destination.className
                }
                else -> {
                    destination?.navigatorName ?: throw java.lang.RuntimeException("Cannot navigate")
                }
            }
            setFragmentResultListener(key) { _, bundle ->
                clearFragmentResultListener(key)
                clearFragmentResult(key)
                cont.resume(bundle.getParcelable("result")!!)
            }
        }
    }
}
