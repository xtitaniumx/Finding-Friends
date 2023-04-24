package com.example.peoplefind.di

import com.example.peoplefind.presentation.MainViewModel
import com.example.peoplefind.presentation.TokenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(registerAccountUseCase = get())
    }

    viewModel {
        TokenViewModel(
            getUserTokensUseCase = get(),
            saveUserTokensUseCase = get(),
            deleteUserTokensUseCase = get()
        )
    }
}