package com.example.peoplefind.di

import com.example.peoplefind.domain.usecase.DeleteUserDataUseCase
import com.example.peoplefind.domain.usecase.DeleteUserTokensUseCase
import com.example.peoplefind.domain.usecase.FillQuestionnaireUseCase
import com.example.peoplefind.domain.usecase.GetRecommendationsUseCase
import com.example.peoplefind.domain.usecase.GetTokenUseCase
import com.example.peoplefind.domain.usecase.GetUserIdUseCase
import com.example.peoplefind.domain.usecase.GetUserLoginStateUseCase
import com.example.peoplefind.domain.usecase.LoginAccountUseCase
import com.example.peoplefind.domain.usecase.LogOutAccountUseCase
import com.example.peoplefind.domain.usecase.RegisterAccountUseCase
import com.example.peoplefind.domain.usecase.SaveTokensUseCase
import com.example.peoplefind.domain.usecase.SaveUserDataUseCase
import com.example.peoplefind.domain.usecase.UpdateQuestionnaireUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        RegisterAccountUseCase(userRepository = get())
    }

    factory {
        LoginAccountUseCase(userRepository = get())
    }

    factory {
        LogOutAccountUseCase(userRepository = get())
    }

    factory {
        GetTokenUseCase.GetAccessToken(tokenRepository = get())
    }

    factory {
        GetTokenUseCase.GetRefreshToken(tokenRepository = get())
    }

    factory {
        GetTokenUseCase.GetStreamChatToken(tokenRepository = get())
    }

    factory {
        GetUserIdUseCase(userRepository = get())
    }

    factory {
        GetUserLoginStateUseCase(userRepository = get())
    }

    factory {
        SaveTokensUseCase(tokenRepository = get())
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

    factory {
        GetRecommendationsUseCase(questionnaireRepository = get())
    }

    factory {
        FillQuestionnaireUseCase(questionnaireRepository = get())
    }

    factory {
        UpdateQuestionnaireUseCase(questionnaireRepository = get())
    }
}