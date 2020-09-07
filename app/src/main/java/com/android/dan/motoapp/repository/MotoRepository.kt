package com.android.dan.motoapp.repository

import com.android.dan.motoapp.api.Api
import com.android.dan.motoapp.database.moto.MotoDao
import com.android.dan.motoapp.utils.Result
import javax.inject.Inject

class MotoRepository (var api: Api, val motoDao: MotoDao) {

    suspend fun getMoto(): Result {
        val response = api.getMoto()
        return if (response.isSuccessful) {
            motoDao.deleteAllMoto()
            motoDao.addAllMoto(response.body()!!)
            Result.SuccessResult(motoDao.getAllMoto())
        } else
            Result.ExceptionResult(response.errorBody()?.string() ?: "Something goes wrong")
    }
}