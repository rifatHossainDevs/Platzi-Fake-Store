package com.epsports.platzifakestore.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epsports.platzifakestore.apiService.Service
import com.epsports.platzifakestore.model.Category
import com.epsports.platzifakestore.model.ProductByCategory
import com.epsports.platzifakestore.model.ProductDetail
import com.epsports.platzifakestore.model.Products
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val _products = MutableLiveData<List<Products>>()
    val products: LiveData<List<Products>> = _products

    private val _productsByCategory = MutableLiveData<List<ProductByCategory>>()
    val productsByCategory: LiveData<List<ProductByCategory>> = _productsByCategory

    private val _productsByTitle = MutableLiveData<List<ProductDetail>>()
    val productsByTitle: LiveData<List<ProductDetail>> = _productsByTitle

    init {
        getCategories()
        getProducts()
    }

    fun getCategories() {
        viewModelScope.launch {
            val response = Service.platziApiService.getCategories()
            _categories.value = response.body()
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            val response = Service.platziApiService.getProducts()
            _products.value = response.body()
        }
    }

    fun getProductByCategory(categorySlug: String) {
        viewModelScope.launch {
            val response = Service.platziApiService.getProductsByCategory(categorySlug)
            _productsByCategory.value = response.body()
        }
    }

    fun getProductByTitle(productTitle: String) {
        viewModelScope.launch {
            val response = Service.platziApiService.getProductsByTitle(productTitle)
            _productsByTitle.value = response.body()
        }
    }
}
