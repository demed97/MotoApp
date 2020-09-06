package com.android.dan.motoapp.api

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor (var sharedPreferences: SharedPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
//val editor = sharedPreferences.edit()
        val original = chain.request()

        println(original.url())

        val requestBuilder : Request.Builder
        if(original.url().toString() == "https://fluxjwt.herokuapp.com/authorize/login"){
            requestBuilder = original.newBuilder()
            println(">>>>>>>>ORIGINAL URL FROM Header Int ${original.url()} ${original.headers()}")
        }else{
            requestBuilder = original.newBuilder()
                .header("Authorization","Bearer ${sharedPreferences.getString("token","empty")}")
            println(">>>>>>>>ORIGINAL URL FROM Header Int ${sharedPreferences.getString("token","empty")}")
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}