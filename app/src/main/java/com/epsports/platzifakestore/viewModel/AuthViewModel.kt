package com.epsports.platzifakestore.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epsports.platzifakestore.apiService.Service
import com.epsports.platzifakestore.model.RequestLogin
import com.epsports.platzifakestore.model.ResponseDTO
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _login = MutableLiveData<ResponseDTO>()
    val login: LiveData<ResponseDTO> = _login

    init {

    }

    fun authLogin(login: RequestLogin) {
        viewModelScope.launch {
            val response = Service.platziApiService.authLogin(login)
            _login.value = response.body()
        }
    }
}
