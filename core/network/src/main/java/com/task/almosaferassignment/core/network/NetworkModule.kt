package com.task.almosaferassignment.core.network

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.serjltt.moshi.adapters.DeserializeOnly
import com.serjltt.moshi.adapters.ElementAt
import com.serjltt.moshi.adapters.FallbackEnum
import com.serjltt.moshi.adapters.FallbackOnNull
import com.serjltt.moshi.adapters.FirstElement
import com.serjltt.moshi.adapters.LastElement
import com.serjltt.moshi.adapters.SerializeNulls
import com.serjltt.moshi.adapters.SerializeOnly
import com.serjltt.moshi.adapters.SerializeOnlyNonEmpty
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_IN_MINUTES = 2L

    @Provides
    @Reusable
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        authenticationInterceptor: AuthenticationInterceptor
    ): OkHttpClient {
        val networkFlipperPlugin = AndroidFlipperClient.getInstance(context)
            .getPlugin<NetworkFlipperPlugin>(NetworkFlipperPlugin.ID)
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
            .readTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
            .addInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin, true))
            .addInterceptor(authenticationInterceptor)
            .build()
    }

    @Provides
    @Reusable
    fun provideRetrofit(okHttpClient: OkHttpClient, factory: Converter.Factory): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.API_BASE_URL).addConverterFactory(factory).client(okHttpClient)
            .build()
    }

    @Provides
    @Reusable
    fun provideConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Reusable
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(SerializeNulls.ADAPTER_FACTORY)
            .add(FirstElement.ADAPTER_FACTORY)
            .add(LastElement.ADAPTER_FACTORY)
            .add(ElementAt.ADAPTER_FACTORY)
            .add(FallbackOnNull.ADAPTER_FACTORY)
            .add(FallbackEnum.ADAPTER_FACTORY)
            .add(Wrapped.ADAPTER_FACTORY)
            .add(SerializeOnly.ADAPTER_FACTORY)
            .add(DeserializeOnly.ADAPTER_FACTORY)
            .add(SerializeOnlyNonEmpty.ADAPTER_FACTORY)
            .build()
    }
}
