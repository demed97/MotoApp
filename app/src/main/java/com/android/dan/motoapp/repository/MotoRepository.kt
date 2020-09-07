package com.android.dan.motoapp.repository

import com.android.dan.motoapp.api.Api
import com.android.dan.motoapp.utils.Result
import javax.inject.Inject

class MotoRepository @Inject constructor(var api: Api) {

    suspend fun getMoto(): Result {
        val response = api.getMoto()
        return if (response.isSuccessful) {
            Result.SuccessResult(response.body()!!)
        } else
            Result.ExceptionResult(response.errorBody()?.string() ?: "Something goes wrong")
    }
}