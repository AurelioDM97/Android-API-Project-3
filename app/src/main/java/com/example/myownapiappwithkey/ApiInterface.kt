package com.example.myownapiappwithkey

import com.example.myownapiappwithkey.api.JokeResponse
import com.example.myownapiappwithkey.api.JokesDataBody
import retrofit2.http.GET

interface ApiInterface {

    @GET("/random/joke")
        suspend fun getJokes() : JokeResponse
    }