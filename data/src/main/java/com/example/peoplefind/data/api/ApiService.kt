package com.example.peoplefind.data.api

import com.example.peoplefind.domain.model.request.ChangePasswordParam
import com.example.peoplefind.domain.model.request.FillQuestionnaireParam
import com.example.peoplefind.domain.model.request.LoginAccountParam
import com.example.peoplefind.domain.model.request.PutAQuestionnaireGradeParam
import com.example.peoplefind.domain.model.request.RefreshAccessTokenParam
import com.example.peoplefind.domain.model.request.RegisterAccountParam
import com.example.peoplefind.domain.model.request.UpdateAccountInfoParam
import com.example.peoplefind.domain.model.request.UpdateQuestionnaireParam
import com.example.peoplefind.domain.model.response.AuthInfo
import com.example.peoplefind.domain.model.response.Questionnaire
import com.example.peoplefind.domain.model.response.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    /**
     * User requests
     */
    @POST("/api/v1/User/GetById/{userId}")
    fun getUserById(@Path("userId") userId: String): Call<User>

    @POST("/api/v1/User/GetAccessToken")
    fun refreshToken(@Body request: RefreshAccessTokenParam): Call<AuthInfo>

    @POST("/api/v1/User/Registration")
    fun registerUser(@Body request: RegisterAccountParam): Call<AuthInfo>

    @POST("/api/v1/User/Login")
    fun loginUser(@Body request: LoginAccountParam): Call<AuthInfo>

    @POST("/api/v1/User/Logout")
    fun logoutUser(): Call<Unit>

    @PATCH("/api/v1/User/Update/{userId}")
    fun updateUserInfo(@Path("userId") userId: String, @Body updateRequest: UpdateAccountInfoParam): Call<Unit>

    @PATCH("/api/v1/User/ChangePassword")
    fun changePassword(@Body changeRequest: ChangePasswordParam): Call<Unit>

    /**
     * Questionnaire requests
     */
    @GET("/api/v1/Questionnaire/GetRecommendations")
    fun getRecommendations(): Call<List<Questionnaire>>

    @GET("/api/v1/Questionnaire/GetByUserId/{userId}")
    fun getQuestionnaireByUserId(@Path("userId") userId: String): Call<Questionnaire>

    @PATCH("/api/v1/Questionnaire/FillOutAForm")
    fun fillQuestionnaire(@Body request: FillQuestionnaireParam): Call<Unit>

    @PATCH("/api/v1/Questionnaire/PutAGrade")
    fun putAQuestionnaireGrade(@Body request: PutAQuestionnaireGradeParam): Call<Unit>

    @PATCH("/api/v1/Questionnaire/Update")
    fun updateQuestionnaire(@Body request: UpdateQuestionnaireParam): Call<Unit>

    @PATCH("/api/v1/Questionnaire/ResetStatistics")
    fun resetQuestionnaireStatistics(): Call<Unit>

    @PATCH("/api/v1/Questionnaire/Publish")
    fun publishQuestionnaire(): Call<Unit>

    @PATCH("/api/v1/Questionnaire/RemoveFromPublication")
    fun removeQuestionnaireFromPublication(): Call<Unit>
}