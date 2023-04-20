package com.example.peoplefind.data.repository

import com.example.peoplefind.domain.model.NetworkResult
import com.example.peoplefind.domain.model.response.ErrorItem
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): NetworkResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiToBeCalled()

                if (response.isSuccessful) {
                    NetworkResult.Success(data = response.body()!!)
                } else {
                    val errorResponse = convertErrorBody(response.errorBody())
                    NetworkResult.Error(errorMessage = errorResponse?.failureMessage ?: "Внутренняя ошибка сервера, повторите попытку позже")
                }
                // Подумать над сообщениями об ошибке
            } catch (e: HttpException) {
                NetworkResult.Error(errorMessage = e.message ?: "Сервер не отвечает, повторите попытку позже")
            } catch (e: IOException) {
                Timber.e(e, e.message)
                NetworkResult.Error(errorMessage = "Пожалуйста, проверьте подключение к сети")
            } catch (e: Exception) {
                Timber.e(e, e.message)
                NetworkResult.Error(errorMessage = "Что-то пошло не так")
            }
        }
    }

    private fun convertErrorBody(errorBody: ResponseBody?): ErrorItem? {
        return try {
            errorBody?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorItem::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (e: Exception) {
            null
        }
    }
}