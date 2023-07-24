package com.example.myownapiappwithkey.network.remote.dto

data class JokeResponse(
    val body: List<JokesDataBody>,
    val success: Boolean
)