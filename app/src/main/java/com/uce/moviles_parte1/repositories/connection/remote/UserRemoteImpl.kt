package com.uce.moviles_parte1.repositories.connection.remote

import com.uce.moviles_parte1.dto.remote.dto.UserDtoRemote

class UserRemoteImpl : UserRemote {
    override fun getAllUsers(): Result<List<UserDtoRemote>> {
        TODO("Not yet implemented")
    }

    override fun getOneUser(user: UserDtoRemote): Result<UserDtoRemote> {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: UserDtoRemote): Result<UserDtoRemote> {
        TODO("Not yet implemented")
    }
}