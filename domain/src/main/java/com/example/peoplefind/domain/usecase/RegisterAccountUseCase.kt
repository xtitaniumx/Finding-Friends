package com.example.peoplefind.domain.usecase

import com.example.peoplefind.domain.model.ApiResult
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.response.AuthInfo
import com.example.peoplefind.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterAccountUseCase(private val userRepository: UserRepository) {
    operator fun invoke(registerAccountParam: RegisterAccountParam): Flow<ApiResult<AuthInfo>> {
        when {
            registerAccountParam.email.isEmpty() ||
                    registerAccountParam.password.isEmpty() ||
                    registerAccountParam.passwordConfirm.isEmpty() -> {
                return flow {
                    emit(
                        ApiResult.Failure(
                            message = "Одно или несколько обязательных полей не были заполнены",
                            error = "HTTP 400"
                        )
                    )
                }
            }

            registerAccountParam.password != registerAccountParam.passwordConfirm -> {
                return flow {
                    emit(
                        ApiResult.Failure(
                            message = "Пароли не совпадают",
                            error = "HTTP 400"
                        )
                    )
                }
            }

            registerAccountParam.password.length < 5 -> {
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

        return userRepository.registerAccount(param = registerAccountParam)
    }
}