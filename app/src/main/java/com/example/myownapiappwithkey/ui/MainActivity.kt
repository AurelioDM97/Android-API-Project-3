package com.example.myownapiappwithkey.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.myownapiappwithkey.MyApplication
import com.example.myownapiappwithkey.databinding.ActivityMainBinding
import com.example.myownapiappwithkey.network.RetrofitInstance
import com.example.myownapiappwithkey.network.remote.JokesAPI
import com.example.myownapiappwithkey.viewmodel.MainViewModel
import com.example.myownapiappwithkey.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var jokesViewModel : MainViewModel
    private var api = RetrofitInstance.jokesAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("userPrefs", MODE_PRIVATE)

        binding.activityEdit.addTextChangedListener {
            sharedPref.edit()
                .putString("userName", it.toString())
                .apply()
        }
        val username = sharedPref.getString("userName", null)

        val repository = (application as MyApplication).jokeRepository

        jokesViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        lifecycleScope.launch {
            jokesViewModel.getJokesFlow().collect {
                val joke = it[0]
                binding.jokeText.text = "${joke.setup}\n${joke.punchline}"
                Log.d("JOKES", "ID: ${it.size}")
            }
        }
        binding.jokeButton.setOnClickListener() {
            jokesViewModel.generateNewJoke()
            lifecycleScope.launch {
                val response = api.getJokes()
                val jokesSetup = response.body[0].setup
                val jokesPunchline = response.body[0].punchline
                binding.jokeText.text = "$jokesSetup\n$jokesPunchline"
            }
        }
        /*jokesViewModel.jokes.observe(this) { it ->
            it.body.iterator().forEach {
                Log.d("JOKES", "id: ${it.id}")
            }
        }*/
        //jokesViewModel.getJokesNetworkCall()
    }
}


