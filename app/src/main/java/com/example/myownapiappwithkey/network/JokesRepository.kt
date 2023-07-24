package com.example.myownapiappwithkey.network

import com.example.myownapiappwithkey.network.remote.JokesAPI
import com.example.myownapiappwithkey.network.remote.dto.JokesDataBody
import com.example.myownapiappwithkey.network.room.JokesDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import java.util.UUID

class JokesRepository(
    private val jokesApi : JokesAPI,
    private val jokesDatabase : JokesDatabase,
) {
     private val jokesFlow = MutableSharedFlow<List<JokesDataBody>>()

    suspend fun getJoke() {
        val response = jokesApi.getJokes()
        if (response.success) {
            val jokeWithId = response.body[0].copy(id = UUID.randomUUID().toString())
            jokesDatabase.jokesDao().insertJokes(jokeWithId)

            val jokesFromDatabase = jokesDatabase.jokesDao().getJokes()
            jokesFlow.emit(jokesFromDatabase)
        } else {
            val jokesFromDatabase = jokesDatabase.jokesDao().getJokes()
            jokesFlow.emit(jokesFromDatabase)
        }
    }
    //fun getJokesFlow(): SharedFlow<List<JokesDataBody>> = jokesFlow
}