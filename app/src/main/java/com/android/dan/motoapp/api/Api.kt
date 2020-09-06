package com.android.dan.motoapp.api

import com.android.dan.motoapp.entities.Login
import com.android.dan.motoapp.entities.Token
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @POST("authorize/login")
   suspend fun login(@Body login: Login) : Response<Token>

}