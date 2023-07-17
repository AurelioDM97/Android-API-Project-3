package com.example.myownapiappwithkey.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myownapiappwithkey.network.NetworkProvider
import com.example.myownapiappwithkey.network.dto.JokeResponse
import com.example.myownapiappwithkey.network.dto.JokesDataBody
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    val jokes = MutableSharedFlow<JokesDataBody>()
    var jokesApi = NetworkProvider()

    fun getJokesNetworkCall() {
        viewModelScope.launch {
            try {
                val response = jokesApi.getJokesData()
                if(response.success) {
                    jokes.emit(response.body[0])
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
}