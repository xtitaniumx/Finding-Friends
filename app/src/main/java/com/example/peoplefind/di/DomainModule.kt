package com.example.peoplefind.di

import com.example.peoplefind.domain.usecase.LoginAccountUseCase
import com.example.peoplefind.domain.usecase.LogOutAccountUseCase
import com.example.peoplefind.domain.usecase.RegisterAccountUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        RegisterAccountUseCase(userRepository = get())
    }

    factory {
        LoginAccountUseCase(userRepository = get())
    }

    factory {
        LoginAccountUseCase(userRepository = get())
    }

    factory {
        LogOutAccountUseCase(userRepository = get())
    }
}