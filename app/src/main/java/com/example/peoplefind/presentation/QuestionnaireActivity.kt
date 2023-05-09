package com.example.peoplefind.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityQuestionnaireBinding

class QuestionnaireActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionnaireBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaireBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        buttonNext.setOnClickListener {
            val intent = Intent(this@QuestionnaireActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}