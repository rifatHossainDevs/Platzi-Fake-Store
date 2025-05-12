package com.epsports.platzifakestore.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epsports.platzifakestore.apiService.Service
import com.epsports.platzifakestore.model.ResponseCategories
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _categories = MutableLiveData<ResponseCategories>()
    val categories: LiveData<ResponseCategories> = _categories

    fun getCategories() {
        viewModelScope.launch {
            val response = Service.platziApiService.getCategories()
            _categories.value = response.body()
        }
    }

}