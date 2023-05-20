package com.task.almosaferassignment.core.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthenticationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("api_key", "114fe6670282f6a632638661e5e86dee").build()
        val requestBuilder = request.newBuilder().url(url)
        return chain.proceed(requestBuilder.build())
    }
}
