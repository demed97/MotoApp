package com.android.dan.motoapp.database.moto

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.dan.motoapp.entities.Moto

@Database(entities = [Moto::class], version = 1)
abstract class MotoDatabase : RoomDatabase() {

    abstract fun getMotoDao() : MotoDao
}