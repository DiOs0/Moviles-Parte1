package com.uce.moviles_parte1.data.local.dto


import androidx.room.Entity;
import androidx.room.PrimaryKey

@Entity
data class ProfilesDto(

    @PrimaryKey(autoGenerate = true)
    val id:Int,

    val idFirebase:String,
    val name:String,
    val image:String
)
