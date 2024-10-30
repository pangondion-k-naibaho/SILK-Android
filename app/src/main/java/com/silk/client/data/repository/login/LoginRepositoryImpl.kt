package com.silk.client.data.repository.login

import com.silk.client.data.model.request.login.LoginRequest
import com.silk.client.data.model.response.login.LoginResponse
import com.silk.client.data.remote.ApiService

class LoginRepositoryImpl(private val apiService: ApiService) : LoginRepository {
    override suspend fun loginUser(email: String, password: String): Result<LoginResponse> {
        return try {
            val request = LoginRequest(email, password)
            val response = apiService.loginUser(request)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Error ${response.code()} : ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}