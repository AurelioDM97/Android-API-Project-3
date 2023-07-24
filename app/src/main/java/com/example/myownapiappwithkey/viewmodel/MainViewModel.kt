package com.example.myownapiappwithkey.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myownapiappwithkey.network.JokesRepository
import com.example.myownapiappwithkey.network.remote.JokesAPI
import com.example.myownapiappwithkey.network.remote.dto.JokeResponse
import com.example.myownapiappwithkey.network.remote.dto.JokesDataBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repository: JokesRepository) : ViewModel() {

    private val _jokes = MutableSharedFlow<List<JokesDataBody>>()
    val jokes: SharedFlow<List<JokesDataBody>> get() = _jokes

    init {
        viewModelScope.launch {
            try {
                repository.getJoke()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun generateNewJoke() {
        viewModelScope.launch {
            repository.getJoke()
        }
    }
    fun getJokesFlow(): SharedFlow<List<JokesDataBody>> = _jokes
}
    //val jokesFlow : MutableSharedFlow<JokesDataBody> get() = repository.jokesFlow

