package com.example.peoplefind.di

import com.example.peoplefind.data.api.ApiClient
import com.example.peoplefind.data.repository.QuestionnaireRepositoryImpl
import com.example.peoplefind.data.repository.TokenRepositoryImpl
import com.example.peoplefind.data.repository.UserRepositoryImpl
import com.example.peoplefind.domain.repository.QuestionnaireRepository
import com.example.peoplefind.domain.repository.TokenRepository
import com.example.peoplefind.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> {
        UserRepositoryImpl(apiClient = ApiClient(), context = get())
    }

    single<TokenRepository> {
        TokenRepositoryImpl(context = get())
    }

    single<QuestionnaireRepository> {
        QuestionnaireRepositoryImpl(apiClient = ApiClient(), context = get())
    }
}