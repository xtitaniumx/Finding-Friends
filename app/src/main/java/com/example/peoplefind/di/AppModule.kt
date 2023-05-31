package com.example.peoplefind.di

import com.example.peoplefind.domain.usecase.GetTokenUseCase
import com.example.peoplefind.presentation.vm.AuthViewModel
import com.example.peoplefind.presentation.vm.HomeViewModel
import com.example.peoplefind.presentation.vm.MessengerViewModel
import com.example.peoplefind.presentation.vm.ProfileViewModel
import com.example.peoplefind.presentation.vm.QuestionnaireViewModel
import com.example.peoplefind.presentation.vm.TokenViewModel
import com.example.peoplefind.presentation.vm.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        WelcomeViewModel(getUserLoginStateUseCase = get())
    }

    viewModel {
        AuthViewModel(
            registerAccountUseCase = get(),
            loginAccountUseCase = get(),
            saveUserDataUseCase = get()
        )
    }

    viewModel {
        TokenViewModel(
            getAccessTokenUseCase = GetTokenUseCase.GetAccessToken(tokenRepository = get()),
            getRefreshTokenUseCase = GetTokenUseCase.GetRefreshToken(tokenRepository = get()),
            getStreamChatTokenUseCase = GetTokenUseCase.GetStreamChatToken(tokenRepository = get()),
            saveUserTokensUseCase = get(),
            deleteUserTokensUseCase = get()
        )
    }

    viewModel {
        QuestionnaireViewModel(
            fillQuestionnaireUseCase = get(),
            updateQuestionnaireUseCase = get()
        )
    }

    viewModel {
        HomeViewModel(
            getRecommendationsUseCase = get(),
            putAQuestionnaireGradeUseCase = get()
        )
    }

    viewModel {
        MessengerViewModel(
            getUserIdUseCase = get(),
            getStreamChatTokenUseCase = get()
        )
    }

    viewModel {
        ProfileViewModel(
            logoutAccountUseCase = get(),
            deleteUserDataUseCase = get()
        )
    }
}