package com.uce.moviles_parte1.logic.usercases

import android.net.Uri
import com.uce.moviles_parte1.data.remote.dto.UserDtoRemote
import com.uce.moviles_parte1.repositories.UserRepository

class SaveUserUC (val userRepository: UserRepository,val uploadImgeCloudinary:UploadImageInCloudinary){

    suspend fun saveUser(
        user: UserDtoRemote,
        uri: Uri
        ): Result<UserDtoRemote>{
        val image= uploadImgeCloudinary.invoke(uri)

        return userRepository.saveUser(user)
    }


}