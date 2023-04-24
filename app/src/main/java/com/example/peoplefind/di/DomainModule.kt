package com.example.peoplefind.di

import com.example.peoplefind.domain.usecase.DeleteUserTokensUseCase
import com.example.peoplefind.domain.usecase.GetUserTokensUserCase
import com.example.peoplefind.domain.usecase.LoginAccountUseCase
import com.example.peoplefind.domain.usecase.LogOutAccountUseCase
import com.example.peoplefind.domain.usecase.RegisterAccountUseCase
import com.example.peoplefind.domain.usecase.SaveUserTokensUseCase
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

    factory {
        GetUserTokensUserCase(tokenRepository = get())
    }

    factory {
        SaveUserTokensUseCase(tokenRepository = get())
    }

    factory {
        DeleteUserTokensUseCase(tokenRepository = get())
    }
}