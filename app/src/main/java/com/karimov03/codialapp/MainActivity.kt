package com.karimov03.codialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karimov03.codialapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnKurslar.setOnClickListener {

        }
    }
}