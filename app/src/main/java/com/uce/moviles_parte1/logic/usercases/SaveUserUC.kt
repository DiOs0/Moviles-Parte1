package com.uce.moviles_parte1.logic.usercases

import com.google.firebase.firestore.FirebaseFirestore
import com.uce.moviles_parte1.dto.remote.dto.UserDtoRemote
import com.uce.moviles_parte1.repositories.connection.UserRepository
import kotlinx.coroutines.tasks.await

class SaveUserUC (val userRepository: UserRepository){

    suspend fun saveUser(user: UserDtoRemote): Result<UserDtoRemote>{
        return userRepository.saveUser(user)
    }


}