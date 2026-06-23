package com.uce.moviles_parte1.data.local.dto.remote.dto.users

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)