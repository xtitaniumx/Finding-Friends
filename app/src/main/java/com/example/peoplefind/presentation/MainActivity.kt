package com.example.peoplefind.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.peoplefind.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private val tokenViewModel by viewModel<TokenViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        /*buttonRegister.setOnClickListener {
            viewModel.registerAccount(
                email = "abc@gmail.com",
                birthDate = "2023-04-23T11:49:12.491Z",
                password = "abc",
                passwordConfirm = "abc",
                role = Role.Admin
            )
        }*/

        /*viewModel.authInfoFlowError.observe(this@MainActivity) {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
        }*/

        /*viewModel.authInfo.observe(this@MainActivity) { result ->
            result.onLoading {
                //Toast.makeText(this@MainActivity, "Loading...", Toast.LENGTH_LONG).show()
                textAccountInfo.loadSkeleton()
            }.onSuccess {
                tokenViewModel.saveToken(
                    token = it.accessToken, refreshToken = it.refreshToken
                )
                //Toast.makeText(this@MainActivity, it.userId, Toast.LENGTH_LONG).show()
                textAccountInfo.text = it.userId
                textAccountInfo.hideSkeleton()
            }.onFailure { errorMessage, code ->
                textAccountInfo.text = "Message: $errorMessage, code: $code"
                textAccountInfo.hideSkeleton()
            }
        }*/
    }
}