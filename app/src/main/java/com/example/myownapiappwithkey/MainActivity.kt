package com.example.myownapiappwithkey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.myownapiappwithkey.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    private const val API_KEY = "28b8f1730amsh2a3993a580ce6a6p195824jsn2bef194831cb"
    private const val BASE_URL = "https://dad-jokes.p.rapidapi.com/random/joke/"
    private const val HOST_URL = "dad-jokes.p.rapidapi.com"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getJokes()


        binding.jokeButton.setOnClickListener() {
            getJokes()
        }

    }

    private fun getJokes() {

        val client = OkHttpClient.Builder()
            .addInterceptor(JokesDataInterceptor(API_KEY, HOST_URL))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val jokesApi = retrofit.create(ApiInterface::class.java)

        lifecycleScope.launch {
            try {
                val response = jokesApi.getJokes()

                if(response.success) {
                    val jokeData = response.body

                    val (setup, punchline) = jokeData[0]

                    binding.jokeText.text = "$setup\n\n$punchline"

                } else {
                    binding.jokeText.text = "Non ci sono più barzellette disponibili"
                }


            } catch (e : Exception) {
                binding.jokeText.text = "Errore connessione al server."
            }
        }

    }


    class JokesDataInterceptor(private val apiKey : String, private val hostUrl : String) :
        Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            val keyRequest = request.newBuilder()
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host",hostUrl )
                .build()

            return chain.proceed(keyRequest)
        }

    }
}




