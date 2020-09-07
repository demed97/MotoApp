package com.android.dan.motoapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Moto(
    @PrimaryKey
    var id: String,
    var userId: String,
    var model: String,
    var volume: String,
) {
//    @PrimaryKey(autoGenerate = true)
//    var dataId: Int = 0
}