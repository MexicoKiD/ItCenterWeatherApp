package com.itCenter.weatherApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import com.itCenter.weatherApp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}