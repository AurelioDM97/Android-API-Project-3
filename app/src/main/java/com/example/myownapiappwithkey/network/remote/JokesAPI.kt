package com.example.myownapiappwithkey.network.remote

import com.example.myownapiappwithkey.network.remote.dto.JokeResponse
import retrofit2.http.GET

interface JokesAPI {
    @GET("/random/joke")
    suspend fun getJokes() : JokeResponse
}