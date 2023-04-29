package com.example.peoplefind.di

import com.example.peoplefind.presentation.RegisterViewModel
import com.example.peoplefind.presentation.TokenViewModel
import com.example.peoplefind.presentation.UserDataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        RegisterViewModel(registerAccountUseCase = get())
    }

    viewModel {
        TokenViewModel(
            getUserTokensUseCase = get(),
            saveUserTokensUseCase = get(),
            deleteUserTokensUseCase = get()
        )
    }

    viewModel {
        UserDataViewModel(
            getUserIdUseCase = get(),
            getUserLoginStateUseCase = get(),
            saveUserDataUseCase = get(),
            deleteUserDataUseCase = get()
        )
    }
}