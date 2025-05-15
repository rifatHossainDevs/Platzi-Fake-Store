package com.epsports.platzifakestore.apiService

import com.epsports.platzifakestore.model.Category
import com.epsports.platzifakestore.model.ProductByCategory
import com.epsports.platzifakestore.model.ProductDetail
import com.epsports.platzifakestore.model.Products
import com.epsports.platzifakestore.model.RegisterUser
import com.epsports.platzifakestore.model.RequestLogin
import com.epsports.platzifakestore.model.RequestRegister
import com.epsports.platzifakestore.model.ResponseDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PlatziApiService {
    @POST("auth/login")
    suspend fun authLogin(@Body requestLogin: RequestLogin): Response<ResponseDTO>

    @POST("users/")
    suspend fun authRegister(@Body requestRegister: RequestRegister): Response<RegisterUser>

    @GET("categories")
    suspend fun getCategories(): Response<List<Category>>

    @GET("products")
    suspend fun getProducts(): Response<List<Products>>

    @GET("products/")
    suspend fun getProductsByCategory(
        @Query("categorySlug") categorySlug: String,
    ): Response<List<ProductByCategory>>

    @GET("products/")
    suspend fun getProductsByTitle(
        @Query("title") productTitle: String,
    ): Response<List<ProductDetail>>
}

object Service {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.escuelajs.co/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val platziApiService: PlatziApiService = retrofit.create(PlatziApiService::class.java)
}
