package com.epsports.platzifakestore.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epsports.platzifakestore.apiService.Service
import com.epsports.platzifakestore.model.RegisterUser
import com.epsports.platzifakestore.model.RequestLogin
import com.epsports.platzifakestore.model.RequestRegister
import com.epsports.platzifakestore.model.ResponseDTO
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _login = MutableLiveData<ResponseDTO?>()
    val login: LiveData<ResponseDTO?> = _login


    private val _register = MutableLiveData<RegisterUser?>()
    val register: LiveData<RegisterUser?> = _register

    private val _loginError = MutableLiveData<String>()
    val loginError: LiveData<String> = _loginError

    private val _registerError = MutableLiveData<String>()
    val registerError: LiveData<String> = _registerError

    fun authLogin(login: RequestLogin) {
        viewModelScope.launch {
            try {
                val response = Service.platziApiService.authLogin(login)
                if (response.isSuccessful && response.body() != null) {
                    _login.value = response.body()
                } else {
                    _loginError.value = "Login failed: ${response.message()}"
                }
            } catch (e: Exception) {
                _loginError.value = "Login error: ${e.localizedMessage}"
            }
        }
    }


    fun authRegister(register: RequestRegister) {
        viewModelScope.launch {
            try {
                val response = Service.platziApiService.authRegister(register)
                if (response.isSuccessful && response.body() != null) {
                    _register.value = response.body()
                } else {
                    _registerError.value = "Registration failed: ${response.message()}"
                }
            } catch (e: Exception) {
                _registerError.value = "Registration error: ${e.localizedMessage}"
            }
        }
    }

}
