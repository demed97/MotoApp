package com.android.dan.motoapp.database.login

import androidx.room.*
import com.android.dan.motoapp.entities.Login

@Dao
interface LoginDao {

    @Query("SELECT * FROM login")
    suspend fun getLogin () : List<Login?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewLogin(login: Login)

    @Query("DELETE FROM login")
    suspend fun deleteLogin()
}