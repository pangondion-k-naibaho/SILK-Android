package com.silk.client.data.repository.login

import com.silk.client.data.model.response.login.LoginResponse

interface LoginRepository {
    suspend fun loginUser(email: String, password: String): Result<LoginResponse>?
}