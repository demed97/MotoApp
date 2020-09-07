package com.android.dan.motoapp.database.login

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.dan.motoapp.entities.Login
import com.android.dan.motoapp.entities.Moto

@Database(entities = [Login::class], version = 1)
abstract class LoginDatabase : RoomDatabase() {

    abstract fun getLoginDao() : LoginDao
}