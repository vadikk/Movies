package com.example.network

import okhttp3.Interceptor
import okhttp3.Response

internal class AuthenticationInterceptor(private val apiKey: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {
        val url = it.url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val newRequest = it.newBuilder()
            .url(url)
            .build()

        chain.proceed(newRequest)
    }
}