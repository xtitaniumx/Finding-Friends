package com.example.peoplefind.data.api

import com.example.peoplefind.domain.model.request.ChangePasswordParam
import com.example.peoplefind.domain.model.request.LoginAccountParam
import com.example.peoplefind.domain.model.request.RefreshTokenParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.request.UpdateAccountInfoParam
import com.example.peoplefind.domain.model.response.AuthInfo
import com.example.peoplefind.domain.model.response.UserItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/api/v1/User/GetById/{userId}")
    fun getUserById(@Path("userId") userId: String): Call<UserItem>

    @POST("/api/v1/User/GetAccessToken")
    fun refreshToken(@Body request: RefreshTokenParam): Call<AuthInfo>

    @POST("/api/v1/User/Registration")
    fun registerUser(@Body request: RegisterAccountParam): Call<AuthInfo>

    @POST("/api/v1/User/Authorization")
    fun loginUser(@Body request: LoginAccountParam): Call<AuthInfo>

    @POST("/api/v1/User/Logout")
    fun logoutUser(): Call<Unit>

    @PATCH("/api/v1/User/Update/{userId}")
    fun updateUserInfo(@Path("userId") userId: String, @Body updateRequest: UpdateAccountInfoParam): Call<Unit>

    @PATCH("/api/v1/User/ChangePassword")
    fun changePassword(@Body changeRequest: ChangePasswordParam): Call<Unit>
}