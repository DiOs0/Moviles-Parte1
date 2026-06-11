package com.uce.moviles_parte1.repositories.connection.remote

import com.uce.moviles_parte1.dto.remote.dto.UserDtoRemote

interface UserRemote {
    fun getAllUsers(): Result<List<UserDtoRemote>>
    fun getOneUser(user: UserDtoRemote): Result<UserDtoRemote>
    fun saveUser(user: UserDtoRemote): Result<UserDtoRemote>
}