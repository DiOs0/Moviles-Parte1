package com.uce.moviles_parte1.data.daos.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.uce.moviles_parte1.data.local.dto.ProfilesDto

@Dao
interface ProfilesDAO {
    @Query("SELECT * FROM ProfilesDto")
    fun getAllProfiles(): List<ProfilesDto>

    @Query("SELECT * FROM ProfilesDto where id=:id")
    fun getOneProfile(id:Int): ProfilesDto

    @Insert(onConflict = REPLACE)
    fun insertProfile(profile: ProfilesDto)

}