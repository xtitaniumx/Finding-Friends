package com.example.peoplefind.domain.model

suspend fun <T : Any> NetworkResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Success<T>) {
        if (data != null) {
            executable(data)
        }
    }
}

suspend fun <T : Any> NetworkResult<T>.onError(
    executable: suspend (e: Throwable?, message: String?) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Error<T>) {
        executable(e, message)
    }
}