package com.example.peoplefind.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityRestoreBinding

class RestoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestoreBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {

    }
}