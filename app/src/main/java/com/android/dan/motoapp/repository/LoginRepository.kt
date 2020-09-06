package com.android.dan.motoapp.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.dan.motoapp.api.Api
import com.android.dan.motoapp.entities.Login
import com.android.dan.motoapp.entities.Token
import com.android.dan.motoapp.utils.Result
import javax.inject.Inject

class LoginRepository @Inject constructor(var api: Api, var sharedPreferences: SharedPreferences) {

    suspend fun apiLogin(login: Login): Result {
        val response = api.login(login)
        return if (response.isSuccessful) {

            println("response code is ${response.code()}")
            Log.d("TAG", "response code is ${response.code()}")

            sharedPreferences.edit()
                .putString("token", response.body()?.token)
                .apply()

            println("onResponse, response body is ${response.body()}")
            Log.d("TAG", "onResponse, response body is ${response.body()}")
            println("token ===>>> ${sharedPreferences.getString("token", "default")}")
            Log.d("TAG", "token ===>>> ${sharedPreferences.getString("token", "default")}")

            Result.SuccessResult(Unit)
        } else {
            Log.d("TAG", "ERROR")
            Result.ExceptionResult(response.errorBody()?.string() ?: "Something goes wrong")
        }
    }

}