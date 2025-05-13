package com.epsports.platzifakestore.apiService

import com.epsports.platzifakestore.model.Category
import com.epsports.platzifakestore.model.ProductByCategory
import com.epsports.platzifakestore.model.Products
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PlatziApiService {
    @GET("categories")
    suspend fun getCategories(): Response<List<Category>>

    @GET("products")
    suspend fun getProducts(): Response<List<Products>>

    @GET("products/")
    suspend fun getProductsByCategory(
        @Query("categorySlug") categorySlug: String
    ): Response<List<ProductByCategory>>
}

object Service {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.escuelajs.co/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val platziApiService: PlatziApiService = retrofit.create(PlatziApiService::class.java)
}
