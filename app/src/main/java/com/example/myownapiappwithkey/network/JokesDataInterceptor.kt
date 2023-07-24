package com.example.myownapiappwithkey.network

import okhttp3.Interceptor
import okhttp3.Response

class JokesDataInterceptor(private val apiKey : String, private val hostUrl : String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val keyRequest = request.newBuilder()
            .addHeader("X-RapidAPI-Key", apiKey)
            .addHeader("X-RapidAPI-Host",hostUrl)
            .build()
        return chain.proceed(keyRequest)
    }
}