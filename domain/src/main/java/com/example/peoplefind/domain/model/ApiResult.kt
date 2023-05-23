package com.example.peoplefind.domain.model

sealed class ApiResult<out T> {
    object Loading: ApiResult<Nothing>()

    data class Success<out T>(
        val data: T?
    ) : ApiResult<T>()

    object EmptySuccess: ApiResult<Nothing>()

    data class Failure(
        val message: String,
        val error: String?
    ) : ApiResult<Nothing>()
}
