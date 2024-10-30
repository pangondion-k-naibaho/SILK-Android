package com.silk.client.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silk.client.data.model.response.login.LoginResponse
import com.silk.client.data.repository.login.LoginRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: LoginRepository) : ViewModel() {
    private val TAG = AuthViewModel::class.java.simpleName

    private var _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> = _loginResponse

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isFail = MutableLiveData<Boolean>()
    val isFail: LiveData<Boolean> = _isFail

    fun loginUser(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.loginUser(email, password)
            if (result!!.isSuccess) {
                _loginResponse.value = result.getOrNull()
                _isFail.value = false // Login berhasil
            } else {
                _isFail.value = true // Login gagal
                Log.e(TAG, "error: ${result.exceptionOrNull()?.message}")
            }
            _isLoading.value = false // Selesai loading
        }
    }
}