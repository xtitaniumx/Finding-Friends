package com.example.peoplefind.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityLoginBinding
import com.example.peoplefind.domain.extension.onFailure
import com.example.peoplefind.domain.extension.onLoading
import com.example.peoplefind.domain.extension.onSuccess
import com.example.peoplefind.presentation.vm.AuthViewModel
import com.example.peoplefind.presentation.vm.TokenViewModel
import com.example.peoplefind.presentation.vm.UserDataViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val authViewModel by viewModel<AuthViewModel>()
    private val tokenViewModel by viewModel<TokenViewModel>()
    private val userDataViewModel by viewModel<UserDataViewModel>()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        buttonLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, QuestionnaireActivity::class.java)
            startActivity(intent)
            /*authViewModel.loginAccount(
                email = editTextEmail.text.toString(),
                password = editTextPassword.text.toString()
            )*/
        }

        textRestorePassword.setOnClickListener {
            val intent = Intent(this@LoginActivity, RestoreActivity::class.java)
            startActivity(intent)
        }

        authViewModel.authInfo.observe(this@LoginActivity) { result ->
            result.onLoading {
                skeletonLogin.showSkeleton()
            }.onSuccess {
                skeletonLogin.showOriginal()
                tokenViewModel.saveToken(
                    token = it.accessToken, refreshToken = it.refreshToken
                )
                userDataViewModel.saveUserData(
                    userId = it.userId, loginState = false
                )
                val intent = Intent(this@LoginActivity, QuestionnaireActivity::class.java)
                startActivity(intent)
            }.onFailure { message, error ->
                skeletonLogin.showOriginal()
                MaterialAlertDialogBuilder(this@LoginActivity)
                    .setTitle(resources.getString(R.string.login_error))
                    .setMessage(message)
                    .show()
            }
        }
    }
}