package com.task.almosaferassignment

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.task.almosaferassignment.navigation.Feature
import com.task.almosaferassignment.navigation.FeatureEntry
import com.task.almosaferassignment.navigation.di.NavigationContainer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Provider

@HiltAndroidApp
internal class AlmosaferAssignmentApplication : Application(), NavigationContainer {

    @Inject
    lateinit var features: Map<Class<*>, @JvmSuppressWildcards Provider<Feature>>

    override fun onCreate() {
        super.onCreate()
        initSoLoader()
        initFlipper()
    }


    private fun initSoLoader() {
        SoLoader.init(this, false)
    }

    private fun initFlipper() {
        if (FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(NetworkFlipperPlugin())
            client.addPlugin(DatabasesFlipperPlugin(this))
            client.addPlugin(SharedPreferencesFlipperPlugin(this))
            client.addPlugin(CrashReporterPlugin.getInstance())
            client.addPlugin(NavigationFlipperPlugin.getInstance())
            client.start()
        }
    }

    override fun <T : FeatureEntry<*, *>> getEntryPoint(cls: Class<T>): T {
        return features[cls]!!.get() as T
    }
}
