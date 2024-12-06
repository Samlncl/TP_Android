package com.example.tp_android.Network

import com.example.tp_android.Product
import retrofit2.http.GET


interface RetrofitService {
    @GET("products")
    suspend fun getData(): List<Product>

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@retrofit2.http.Path("category") category: String): List<Product>

}