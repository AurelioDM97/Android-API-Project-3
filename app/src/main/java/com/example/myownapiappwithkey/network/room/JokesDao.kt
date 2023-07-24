package com.example.myownapiappwithkey.network.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myownapiappwithkey.network.remote.dto.JokesDataBody

@Dao
interface JokesDao {
    @Insert
    suspend fun insertJokes(jokes: JokesDataBody) : Long

    @Query("SELECT * FROM jokes")
    suspend fun getJokes(): List<JokesDataBody>
}