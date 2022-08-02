package com.example.test_global_indo_multimedia.service

import com.example.test_global_indo_multimedia.models.Product
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getProducts() : Call<List<Product>>
}

class RetrofitFactory{
    companion object {
        private val client = OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        ).build()
        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val service: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
    }
}