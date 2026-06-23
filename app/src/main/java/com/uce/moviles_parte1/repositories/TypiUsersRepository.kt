package com.uce.moviles_parte1.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TypiUsersRepository {
    fun getApi(): Retrofit {

        val baseConnection= Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return baseConnection

    }
}