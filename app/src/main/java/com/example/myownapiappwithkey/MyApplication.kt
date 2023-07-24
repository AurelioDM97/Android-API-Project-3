package com.example.myownapiappwithkey

import android.app.Application
import com.example.myownapiappwithkey.network.JokesRepository
import com.example.myownapiappwithkey.network.RetrofitInstance
import com.example.myownapiappwithkey.network.room.JokesDatabase

class MyApplication : Application() {

    lateinit var jokeRepository: JokesRepository

    override fun onCreate() {
        super.onCreate()
        inject()
        //val databaseFile = getDatabasePath("jokesDB")
        //databaseFile.delete()
    }

    private fun inject() {
        val jokesDatabase = JokesDatabase.getDatabase(applicationContext)
        val jokeService = RetrofitInstance.jokesAPI
        jokeRepository = JokesRepository(jokeService, jokesDatabase)
    }
}