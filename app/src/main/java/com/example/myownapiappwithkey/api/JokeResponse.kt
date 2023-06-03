package com.example.myownapiappwithkey.api

data class JokeResponse(
    val body: List<JokesDataBody>,
    val success: Boolean
)