package com.example.apexify.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoadOut (

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val primaryWeapon: String,
    val secondaryWeapon: String

)