package com.uce.moviles_parte1.logic.usercases

import com.google.android.gms.tasks.Tasks.call
import com.uce.moviles_parte1.data.local.dto.remote.dto.users.TypicodeUsersDtoItem
import com.uce.moviles_parte1.repositories.TypiUsersRepository
import com.uce.moviles_parte1.repositories.connection.remote.TypiUsersRemote
import retrofit2.create

class GetAllUsersFromTypiCode {

    suspend fun invoke() : List<TypicodeUsersDtoItem>? {
        val baseApi= TypiUsersRepository().getApi()
        val call=baseApi.create<TypiUsersRemote>().getAllUsersTypi()
        if(call.isSuccessful){
            val items=call.body()
            return items
        }else{
            return emptyList()
        }
    }
}