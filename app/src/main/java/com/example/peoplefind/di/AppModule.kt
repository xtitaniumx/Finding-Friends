package com.example.peoplefind.di

import com.example.peoplefind.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(registerAccountUseCase = get())
    }
}