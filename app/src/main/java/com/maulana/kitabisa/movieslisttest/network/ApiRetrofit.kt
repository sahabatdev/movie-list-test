package com.maulana.kitabisa.movieslisttest.network

import com.maulana.kitabisa.movieslisttest.config.AppConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Maulana Rahmatullah
 * @describe Initiate configuration retrofit
 */
object ApiRetrofit {
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(AppConfig.BASE_URL)
        .client(client)
        .build()

    fun create(): ApiService = retrofit.create(ApiService::class.java)
}