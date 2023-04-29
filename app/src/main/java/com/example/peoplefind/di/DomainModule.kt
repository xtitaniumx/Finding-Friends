package com.example.peoplefind.di

import com.example.peoplefind.domain.usecase.DeleteUserDataUseCase
import com.example.peoplefind.domain.usecase.DeleteUserTokensUseCase
import com.example.peoplefind.domain.usecase.GetUserIdUseCase
import com.example.peoplefind.domain.usecase.GetUserLoginStateUseCase
import com.example.peoplefind.domain.usecase.GetUserTokensUserCase
import com.example.peoplefind.domain.usecase.LoginAccountUseCase
import com.example.peoplefind.domain.usecase.LogOutAccountUseCase
import com.example.peoplefind.domain.usecase.RegisterAccountUseCase
import com.example.peoplefind.domain.usecase.SaveUserDataUseCase
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
        GetUserIdUseCase(userRepository = get())
    }

    factory {
        GetUserLoginStateUseCase(userRepository = get())
    }

    factory {
        SaveUserTokensUseCase(tokenRepository = get())
    }

    factory {
        SaveUserDataUseCase(userRepository = get())
    }

    factory {
        DeleteUserTokensUseCase(tokenRepository = get())
    }

    factory {
        DeleteUserDataUseCase(userRepository = get())
    }
}