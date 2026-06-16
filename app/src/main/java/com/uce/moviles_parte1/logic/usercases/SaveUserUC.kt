package com.uce.moviles_parte1.logic.usercases

import com.google.firebase.firestore.FirebaseFirestore
import com.uce.moviles_parte1.dto.remote.dto.UserDtoRemote
import kotlinx.coroutines.tasks.await

class SaveUserUC {

    suspend fun saveUser(user: UserDtoRemote,db: FirebaseFirestore): Result<UserDtoRemote>{
        var resp= db.collection("users")
            .add(user)
            .await()
            .runCatching {
                user
            }
        return resp
    }


}