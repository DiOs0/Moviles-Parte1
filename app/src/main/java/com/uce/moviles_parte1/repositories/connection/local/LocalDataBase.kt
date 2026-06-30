package com.uce.moviles_parte1.repositories.connection.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uce.moviles_parte1.data.daos.local.ProfilesDAO
import com.uce.moviles_parte1.data.local.dto.ProfilesDto

@Database(entities = [ProfilesDto::class], version = 1)
abstract class LocalDataBase : RoomDatabase(){

    abstract fun profilesDao(): ProfilesDAO
}