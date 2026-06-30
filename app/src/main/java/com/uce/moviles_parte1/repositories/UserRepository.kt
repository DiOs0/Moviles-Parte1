package com.uce.moviles_parte1.repositories

import com.uce.moviles_parte1.data.local.dto.ProfilesDto
import com.uce.moviles_parte1.data.remote.dto.UserDtoRemote
import com.uce.moviles_parte1.repositories.connection.local.LocalDataBase
import com.uce.moviles_parte1.repositories.connection.remote.UserRemoteImpl

//Es la conclusion de remota con la local
class UserRepository(
    val userRemoteImpl: UserRemoteImpl,
    val dbLocal: LocalDataBase
) {

    suspend fun saveUser(user: UserDtoRemote):Result<UserDtoRemote>{

        val resp : Result<UserDtoRemote>

        val resulteRemote = userRemoteImpl.saveUser(user)
        val usRemote =  resulteRemote.getOrNull()

        if(usRemote != null) {
            var profileDto = ProfilesDto(
                0,
                usRemote.id,
                usRemote.name,
                usRemote.imagen)

            dbLocal.profilesDao().insertProfile(profileDto)
            resp = Result.success(usRemote)
        } else {
            resp = Result.failure(Exception("No se guardo el usuario de forma remota"))
        }

        return resp
    }
    suspend fun getAllUsers(): Result<List<UserDtoRemote>>{
        return userRemoteImpl.getAllUsers()
    }
}