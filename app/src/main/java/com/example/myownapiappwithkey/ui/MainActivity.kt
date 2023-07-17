package com.example.myownapiappwithkey.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.myownapiappwithkey.databinding.ActivityMainBinding
import com.example.myownapiappwithkey.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var jokesViewModel = MainViewModel()

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
        //Log.d("Pref", "$username")b

        lifecycleScope.launch {
            jokesViewModel.jokes.collect {
                    binding.jokeText.text = "${it.setup}\n${it.punchline}"
                }
            }
        binding.jokeButton.setOnClickListener() {
            jokesViewModel.getJokesNetworkCall()
        }
    }
}

