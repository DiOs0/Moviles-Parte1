package com.uce.moviles_parte1.logic.usercases

import com.uce.moviles_parte1.data.local.dto.remote.dto.UserDtoRemote
import com.uce.moviles_parte1.repositories.UserRepository

class SaveUserUC (val userRepository: UserRepository){

    suspend fun saveUser(user: UserDtoRemote): Result<UserDtoRemote>{
        return userRepository.saveUser(user)
    }


}