package com.example.peoplefind.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityChangeQuestionnaireBinding

class ChangeQuestionnaireActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangeQuestionnaireBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeQuestionnaireBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {

    }
}