package com.example.peoplefind.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.peoplefind.databinding.ActivityWelcomeBinding
import com.example.peoplefind.presentation.util.clearStack
import com.example.peoplefind.presentation.vm.WelcomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class WelcomeActivity : AppCompatActivity() {
    private val welcomeViewModel by viewModel<WelcomeViewModel>()
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        welcomeViewModel.loginState.observe(this) {
            Timber.d("Login state is $it")
            if (it == true) {
                Timber.d("Login state is $it")
                val intent = Intent(this, MainActivity::class.java)
                    .apply { clearStack() }
                startActivity(intent)
            } else {
                binding = ActivityWelcomeBinding.inflate(layoutInflater)
                initView()
                setContentView(binding.root)
            }
        }
    }

    private fun initView() = with(binding) {
        buttonStart.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        textLogin.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}