package com.example.myownapiappwithkey.network

import com.example.myownapiappwithkey.network.remote.JokesAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private const val API_KEY = "28b8f1730amsh2a3993a580ce6a6p195824jsn2bef194831cb"
    private const val BASE_URL = "https://dad-jokes.p.rapidapi.com/random/joke/"
    private const val HOST_URL = "dad-jokes.p.rapidapi.com"

    private val client = OkHttpClient.Builder()
        .addInterceptor(JokesDataInterceptor(API_KEY, HOST_URL))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

     val jokesAPI = retrofit.create(JokesAPI::class.java)

    //suspend fun getJokesData() = jokesAPI.getJokes()
}
