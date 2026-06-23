package com.uce.moviles_parte1.repositories

import com.uce.moviles_parte1.data.local.dto.remote.dto.UserDtoRemote
import com.uce.moviles_parte1.repositories.connection.remote.UserRemoteImpl

//Es la conclusion de remota con la local
class UserRepository(val userRemoteImpl: UserRemoteImpl) {

    suspend fun saveUser(user: UserDtoRemote):Result<UserDtoRemote>{
        //Aqui va la logica, si no se va a recuperar de remoto, que lo desvie al local

        return userRemoteImpl.saveUser(user)
    }
    suspend fun getAllUsers(): Result<List<UserDtoRemote>>{
        return userRemoteImpl.getAllUsers()
    }
}