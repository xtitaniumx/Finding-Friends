package com.example.peoplefind.domain.model

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    // We'll wrap our data in this 'Success'
    // class in case of success response from api
    class Success<T>(data: T) : NetworkResult<T>(data = data)

    // We'll pass error message wrapped in this 'Error'
    // class to the UI in case of failure response
    class Error<T>(val e: Throwable? = null, errorMessage: String) : NetworkResult<T>(message = errorMessage)
}