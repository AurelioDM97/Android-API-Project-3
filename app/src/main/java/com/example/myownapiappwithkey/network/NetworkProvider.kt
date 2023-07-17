package com.example.myownapiappwithkey.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY = "28b8f1730amsh2a3993a580ce6a6p195824jsn2bef194831cb"
private const val BASE_URL = "https://dad-jokes.p.rapidapi.com/random/joke/"
private const val HOST_URL = "dad-jokes.p.rapidapi.com"

class NetworkProvider {
    private val client = OkHttpClient.Builder()
        .addInterceptor(JokesDataInterceptor(API_KEY, HOST_URL))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val jokesAPI = retrofit.create(JokesAPI::class.java)

    suspend fun getJokesData() = jokesAPI.getJokes()
}

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