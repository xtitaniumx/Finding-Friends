package com.example.peoplefind.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.peoplefind.databinding.ActivityAboutUserCardBinding

class AboutUserCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutUserCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUserCardBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        buttonBack.setOnClickListener { finish() }

        textProfileInfo.text = intent.getStringExtra("UserName")
        textLocation.text = intent.getStringExtra("UserCity")
    }
}