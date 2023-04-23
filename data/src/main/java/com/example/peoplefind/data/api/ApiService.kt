package com.example.peoplefind.data.api

import com.example.peoplefind.domain.model.request.ChangePasswordParam
import com.example.peoplefind.domain.model.request.LoginAccountParam
import com.example.peoplefind.domain.model.request.RefreshTokenParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.request.UpdateAccountInfoParam
import com.example.peoplefind.domain.model.response.AuthInfo
import com.example.peoplefind.domain.model.response.UserItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/api/v1/User/GetById/{userId}")
    fun getUserById(@Path("userId") userId: String): Response<UserItem>

    @POST("/api/v1/User/GetAccessToken")
    fun refreshToken(@Body request: RefreshTokenParam): Response<AuthInfo>

    @POST("/api/v1/User/Registration")
    fun registerUser(@Body request: RegisterAccountParam): Response<AuthInfo>

    @POST("/api/v1/User/Authorization")
    fun loginUser(@Body request: LoginAccountParam): Response<AuthInfo>

    @POST("/api/v1/User/Logout")
    fun logoutUser(): Response<Unit>

    @PATCH("/api/v1/User/Update/{userId}")
    fun updateUserInfo(@Path("userId") userId: String, @Body updateRequest: UpdateAccountInfoParam): Response<Unit>

    @PATCH("/api/v1/User/ChangePassword")
    fun changePassword(@Body changeRequest: ChangePasswordParam): Response<Unit>
}