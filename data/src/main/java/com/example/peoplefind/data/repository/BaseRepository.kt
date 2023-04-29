package com.example.peoplefind.data.repository

import android.content.Context
import com.example.peoplefind.data.R
import com.example.peoplefind.data.api.ErrorResponse
import com.example.peoplefind.data.api.TokenManager
import com.example.peoplefind.domain.model.ApiResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Call
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

abstract class BaseRepository(private val context: Context) {
    protected val tokenManager = TokenManager(context)

    fun <T> apiRequestFlow(call: suspend () -> Call<T>): Flow<ApiResult<T>> = flow {
        emit(ApiResult.Loading)

        withTimeoutOrNull(20000L) {
            try {
                val response = call().execute()

                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        emit(ApiResult.Success(data))
                    }
                } else {
                    response.errorBody()?.let { error ->
                        error.close()
                        val parsedError: ErrorResponse = Gson().fromJson(error.charStream(), ErrorResponse::class.java)
                        emit(ApiResult.Failure(
                            message = context.resources.getString(R.string.error),
                            error = parsedError.failureMessage,
                            code = 400)
                        )
                    }
                }
            } catch (e: HttpException) {
                emit(ApiResult.Failure(
                    message = context.resources.getString(R.string.http_error),
                    error = e.localizedMessage,
                    code = e.code())
                )
            } catch (e: IOException) {
                emit(ApiResult.Failure(
                    message = context.resources.getString(R.string.network_error),
                    error = e.message,
                    code = 400)
                )
            } catch (e: Exception) {
                Timber.e(e, e.message)
                emit(ApiResult.Failure(
                    message = context.resources.getString(R.string.error),
                    error = e.message,
                    code = 400)
                )
            }
        } ?: emit(ApiResult.Failure(
            message = context.resources.getString(R.string.timeout_error),
            error = null,
            code = 408))
    }.flowOn(Dispatchers.IO)
}