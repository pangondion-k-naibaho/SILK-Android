package com.silk.client.data.remote

import com.silk.client.data.model.request.login.LoginRequest
import com.silk.client.data.model.response.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

}