package com.android.dan.motoapp.database.moto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.dan.motoapp.entities.Moto

@Dao
interface MotoDao {

    @Query("SELECT * FROM moto")
    fun getAllMoto () : List<Moto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllMoto(listMoto: List<Moto>)
}