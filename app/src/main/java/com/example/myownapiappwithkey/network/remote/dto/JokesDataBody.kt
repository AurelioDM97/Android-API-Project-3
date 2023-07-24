package com.example.myownapiappwithkey.network.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "jokes")
data class JokesDataBody(
    @PrimaryKey
    @SerializedName("_id")
    val id: String,
    val setup : String,
    val punchline : String
)
