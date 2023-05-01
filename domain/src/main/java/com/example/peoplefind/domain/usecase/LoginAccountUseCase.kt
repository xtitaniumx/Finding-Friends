package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.LoginAccountParam
import com.example.peoplefind.domain.model.response.AuthInfo
import com.example.peoplefind.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginAccountUseCase(private val userRepository: UserRepository) {
    operator fun invoke(loginAccountParam: LoginAccountParam): Flow<ApiResult<AuthInfo>> {
        when {
            loginAccountParam.email.isEmpty() || loginAccountParam.password.isEmpty() -> {
                return flow {
                    emit(
                        ApiResult.Failure(
                            message = "Одно или несколько обязательных полей не были заполнены",
                            error = "HTTP 400"
                        )
                    )
                }
            }

            loginAccountParam.password.length < 5 -> {
                return flow {
                    emit(
                        ApiResult.Failure(
                            message = "Слишком короткий пароль",
                            error = "HTTP 400"
                        )
                    )
                }
            }
        }

        return userRepository.loginAccount(param = loginAccountParam)
    }
}