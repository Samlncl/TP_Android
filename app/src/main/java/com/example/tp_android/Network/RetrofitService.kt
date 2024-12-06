package com.example.tp_android.Network

import com.example.tp_android.Product
import retrofit2.http.GET


interface RetrofitService {
    @GET("products")
    suspend fun getData(): List<Product>
}