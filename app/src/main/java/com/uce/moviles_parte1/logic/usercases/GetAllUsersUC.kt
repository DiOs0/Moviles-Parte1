package com.uce.moviles_parte1.logic.usercases

import com.uce.moviles_parte1.data.remote.dto.UserDtoRemote
import com.uce.moviles_parte1.repositories.UserRepository

class GetAllUsersUC(val userRepository: UserRepository) {
    suspend fun invoke(): Result<List<UserDtoRemote>>{
        return userRepository.getAllUsers()
    }
}