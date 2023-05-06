package com.example.peoplefind.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityMeetBinding

class MeetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMeetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeetBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {

    }
}