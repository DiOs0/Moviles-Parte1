package com.uce.moviles_parte1.application.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.uce.moviles_parte1.dto.remote.dto.UserDtoRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirstViewModel: ViewModel (){

    //Estoy permitiendo que solo la informacion salga
    //Aqui se va a usar live data, el tipo es el tipo de counter
    //Esto ya es logica(backend), es decir ya se separa
    val counterUI: LiveData<Int>
        get()= _counterUI

    private var _counterUI= MutableLiveData<Int>()

    val userRemote get()= _userRemote
    private var _userRemote= MutableLiveData<UserDtoRemote>()

    fun contador(){
    viewModelScope.launch {

        var counter:Int=0

        for(i in 1..20){
            delay(1000)
            counter++
            _counterUI.value= counter
         }

        }
    }

    fun guardarUsuario(
        user: UserDtoRemote,
        db: FirebaseFirestore
    ){

        viewModelScope.launch {
            val usnew=saveUser(user,db)
            val usr=usnew.getOrNull()

            if(usr!=null){
                userRemote.value=usr
            }else {
                (UserDtoRemote("","Usuario no registrado",""))
            }


        }


    }


    private suspend fun saveUser(user: UserDtoRemote,db: FirebaseFirestore): Result<UserDtoRemote>{
        var resp= db.collection("users")
            .add(user)
            .await()
            .runCatching {
                user
            }
        return resp
    }

}