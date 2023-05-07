package com.example.peoplefind.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        buttonCreateMeet.setOnClickListener {
            if (editTextDateTime.text.isEmpty() ||
                editTextPlace.text.isEmpty() ||
                editTextGoal.text.isEmpty()
            ) {
                return@setOnClickListener
            }
            val intent = Intent().apply {
                putExtra("MeetDate", editTextDateTime.text.toString())
                putExtra("MeetPlace", editTextPlace.text.toString())
                putExtra("MeetGoal", editTextGoal.text.toString())
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}