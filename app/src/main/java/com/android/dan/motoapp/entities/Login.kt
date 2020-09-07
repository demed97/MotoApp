package com.android.dan.motoapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Login(@PrimaryKey var username : String, var password : String) {
}