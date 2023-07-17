package com.example.myownapiappwithkey.network.dto

data class JokeResponse(
    val body: List<JokesDataBody>,
    val success: Boolean
)