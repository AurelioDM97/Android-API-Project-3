package com.example.myownapiappwithkey.network.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myownapiappwithkey.network.remote.dto.JokesDataBody


@Database(entities = [JokesDataBody::class], version = 1)
abstract class JokesDatabase: RoomDatabase() {

    abstract fun jokesDao() : JokesDao

    companion object {
        private var instance : JokesDatabase? = null

        fun getDatabase(context : Context): JokesDatabase {
            if(instance == null){
                instance = Room.databaseBuilder(
                    context,
                    JokesDatabase::class.java,
                    "jokesDB"
                )
                    .build()
            }
            return instance!!
        }
    }
}




