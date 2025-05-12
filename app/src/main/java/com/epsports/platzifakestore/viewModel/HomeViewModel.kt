package com.epsports.platzifakestore.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epsports.platzifakestore.apiService.Service
import com.epsports.platzifakestore.model.Category
import com.epsports.platzifakestore.model.Products
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val _products = MutableLiveData<List<Products>>()
    val products: LiveData<List<Products>> = _products

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            val response = Service.platziApiService.getCategories()
            _categories.value = response.body()
        }
    }

    fun getProducts(){
        viewModelScope.launch {
            val response = Service.platziApiService.getProducts()
            _products.value = response.body()
        }
    }
}
