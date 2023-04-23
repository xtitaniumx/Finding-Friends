package com.example.peoplefind.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.peoplefind.R
import com.example.peoplefind.domain.extension.onFailure
import com.example.peoplefind.domain.extension.onLoading
import com.example.peoplefind.domain.extension.onSuccess
import com.example.peoplefind.domain.model.Role
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private val tokenViewModel by viewModel<TokenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val errorHandler = object: BaseViewModel.CoroutinesErrorHandler {
            override fun onError(message: String) {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.registerAccount(
            coroutinesErrorHandler = errorHandler,
            email = "abc@gmail.com",
            birthDate = "2023-04-23T11:49:12.491Z",
            password = "abc",
            passwordConfirm = "abc",
            role = Role.Admin
        )

        viewModel.authInfo.observe(this) { result ->
            result.onLoading {
                Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show()
            }.onSuccess {
                tokenViewModel.saveToken(
                    token = it.accessToken, refreshToken = it.refreshToken
                )
                Toast.makeText(this, it.userId, Toast.LENGTH_LONG).show()
            }.onFailure { errorMessage, code ->
                Toast.makeText(this, "Message: $errorMessage, code: $code", Toast.LENGTH_LONG).show()
            }
        }
    }
}