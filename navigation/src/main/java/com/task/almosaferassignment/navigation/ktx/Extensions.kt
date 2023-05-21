package com.task.almosaferassignment.navigation.ktx

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.SavedStateHandle
import com.task.almosaferassignment.navigation.di.NavigationContainer


@Suppress("DEPRECATION")
inline fun <reified T : Parcelable> Fragment.navigationArguments(): Lazy<T> {
    return lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable("arguments", T::class.java)!!
        } else {
            requireArguments().getParcelable("arguments")!!
        }
    }
}

@Suppress("DEPRECATION")
inline fun <reified T : Parcelable> Activity.navigationArguments(): Lazy<T> {
    return lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("arguments", T::class.java)!!
        } else {
            intent.getParcelableExtra("arguments")!!
        }
    }
}

inline fun <reified T : Parcelable> SavedStateHandle.navigationArguments(): Lazy<T> {
    return lazy { get<T>("arguments")!! }
}

@Suppress("DEPRECATION")
inline fun <reified T : Parcelable> Bundle.navigationArguments(): Lazy<T> {
    return lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getParcelable("arguments", T::class.java)!!
        } else {
            getParcelable("arguments")!!
        }
    }
}

fun Fragment.navigationContainer(): NavigationContainer {
    return requireActivity().application as NavigationContainer
}

fun FragmentActivity.navigationContainer(): NavigationContainer {
    return application as NavigationContainer
}

@Suppress("UNUSED")
fun <R> Fragment.setResult(result: R) {
    setFragmentResult(this::class.java.name, bundleOf("result" to result))
}
