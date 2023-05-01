package com.example.peoplefind.di

import com.example.peoplefind.presentation.vm.AuthViewModel
import com.example.peoplefind.presentation.vm.TokenViewModel
import com.example.peoplefind.presentation.vm.UserDataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        AuthViewModel(
            registerAccountUseCase = get(),
            loginAccountUseCase = get()
        )
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