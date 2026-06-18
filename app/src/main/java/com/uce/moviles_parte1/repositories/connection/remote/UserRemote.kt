package com.uce.moviles_parte1.repositories.connection.remote

import com.uce.moviles_parte1.dto.remote.dto.UserDtoRemote

interface UserRemote {
    suspend fun getAllUsers(): Result<List<UserDtoRemote>>
    suspend fun getOneUser(user: UserDtoRemote): Result<UserDtoRemote?>
    suspend fun saveUser(user: UserDtoRemote): Result<UserDtoRemote>
}