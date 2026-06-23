package com.uce.moviles_parte1.repositories.connection.remote

import com.uce.moviles_parte1.data.local.dto.remote.dto.users.TypicodeUsersDtoItem
import retrofit2.Response
import retrofit2.http.GET


interface TypiUsersRemote {

    @GET("users")
    suspend fun getAllUsersTypi(): Response<List<TypicodeUsersDtoItem>>


}