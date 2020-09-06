package com.android.dan.motoapp.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

class Controller(val headerInterceptor : Interceptor) {

    private val BASE_URL = "https://fluxjwt.herokuapp.com/"
    private val sHttpClient = OkHttpClient.Builder()
    private val sBuilder = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun createService() : Api{
        sHttpClient.addInterceptor(headerInterceptor)
        val retrofit = sBuilder
            .client(sHttpClient.build())
            .build()
        return retrofit.create(Api::class.java)
    }
}