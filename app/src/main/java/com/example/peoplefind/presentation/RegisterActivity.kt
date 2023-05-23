package com.example.peoplefind.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityRegisterBinding
import com.example.peoplefind.domain.extension.onFailure
import com.example.peoplefind.domain.extension.onLoading
import com.example.peoplefind.domain.extension.onSuccess
import com.example.peoplefind.presentation.util.showErrorDialog
import com.example.peoplefind.presentation.vm.AuthViewModel
import com.example.peoplefind.presentation.vm.TokenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private val authViewModel by viewModel<AuthViewModel>()
    private val tokenViewModel by viewModel<TokenViewModel>()
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        buttonRegister.setOnClickListener {
            authViewModel.registerAccount(
                email = editTextEmail.text.toString(),
                birthdate = editTextBirthdate.text.toString(),
                password = editTextPassword.text.toString(),
                passwordConfirm = editTextPasswordConfirm.text.toString()
            )
        }

        textLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        authViewModel.authInfo.observe(this@RegisterActivity) { result ->
            result.onLoading {
                skeletonRegister.showSkeleton()
            }.onSuccess {
                if (it == null) return@onSuccess

                tokenViewModel.saveToken(
                    token = it.accessToken,
                    refreshToken = it.refreshToken,
                    streamChatToken = it.streamChatToken
                )
                authViewModel.saveUserData(
                    userId = it.userId, loginState = true
                )
                skeletonRegister.showOriginal()
                val intent = Intent(this@RegisterActivity, QuestionnaireActivity::class.java)
                startActivity(intent)
            }.onFailure { message, _ ->
                skeletonRegister.showOriginal()
                showErrorDialog(resources.getString(R.string.register_error), message)
            }
        }
    }
}