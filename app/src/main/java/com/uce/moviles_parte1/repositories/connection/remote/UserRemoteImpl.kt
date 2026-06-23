package com.uce.moviles_parte1.repositories.connection.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.uce.moviles_parte1.data.local.dto.remote.dto.UserDtoRemote
import kotlinx.coroutines.tasks.await

class UserRemoteImpl(val db: FirebaseFirestore) : UserRemote {
    override suspend fun getAllUsers(): Result<List<UserDtoRemote>> = runCatching {
        var lista= arrayListOf<UserDtoRemote>()
        db.collection("users")
            .get()
            .await().forEach {
                lista.add(it.toObject(UserDtoRemote::class.java))
        }
        return@runCatching lista
    }

    override suspend fun getOneUser(user: UserDtoRemote): Result<UserDtoRemote?> = runCatching{
        var lista= arrayListOf<UserDtoRemote>()
        db.collection("users")
            .whereEqualTo("name",user.name)
            .get()
            .await().forEach {
                lista.add(it.toObject(UserDtoRemote::class.java))
            }
        return@runCatching lista.firstOrNull()
    }

    override suspend fun saveUser(user: UserDtoRemote): Result<UserDtoRemote> {
        var resp= db.collection("users")
            .add(user)
            .await()
            .runCatching {
                user
            }
        return resp


    }
}