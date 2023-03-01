package com.example.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
            val networkCallAnswer = doNetworkCall()
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
                binding.tv.text = networkCallAnswer
            }
        }
    }

    private suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer."
    }
}