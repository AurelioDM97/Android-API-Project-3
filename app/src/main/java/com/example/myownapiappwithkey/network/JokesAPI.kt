package com.example.myownapiappwithkey.network

import com.example.myownapiappwithkey.network.dto.JokeResponse
import retrofit2.http.GET

interface JokesAPI {
    @GET("/random/joke")
    suspend fun getJokes() : JokeResponse
}