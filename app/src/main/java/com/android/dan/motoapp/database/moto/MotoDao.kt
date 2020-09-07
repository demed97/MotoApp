package com.android.dan.motoapp.database.moto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.dan.motoapp.entities.Moto

@Dao
interface MotoDao {

    @Query("SELECT * FROM moto")
    suspend fun getAllMoto(): List<Moto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMoto(listMoto: List<Moto>)

    @Query("DELETE FROM moto")
    suspend fun deleteAllMoto()
}