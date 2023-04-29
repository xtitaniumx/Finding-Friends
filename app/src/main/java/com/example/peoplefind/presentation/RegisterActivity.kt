package com.example.peoplefind.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityRegisterBinding
import com.example.peoplefind.domain.extension.onFailure
import com.example.peoplefind.domain.extension.onLoading
import com.example.peoplefind.domain.extension.onSuccess
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private val viewModel by viewModel<RegisterViewModel>()
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
            viewModel.registerAccount(
                email = editTextEmail.text.toString(),
                birthdate = editTextBirthdate.text.toString(),
                password = editTextPassword.text.toString(),
                passwordConfirm = editTextPasswordConfirm.text.toString()
            )
        }

        viewModel.authInfo.observe(this@RegisterActivity) { result ->
            result.onLoading {
                skeletonLayout.showSkeleton()
            }.onSuccess {
                skeletonLayout.showOriginal()
                tokenViewModel.saveToken(
                    token = it.accessToken, refreshToken = it.refreshToken
                )
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
            }.onFailure { message, error, code ->
                skeletonLayout.showOriginal()
                MaterialAlertDialogBuilder(this@RegisterActivity)
                    .setTitle(resources.getString(R.string.register_error))
                    .setMessage(message)
                    .show()
            }
        }
    }
}