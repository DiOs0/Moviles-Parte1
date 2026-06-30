package com.uce.moviles_parte1.application.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uce.moviles_parte1.data.remote.dto.UserDtoRemote
import com.uce.moviles_parte1.data.remote.dto.users.TypicodeUsersDtoItem
import com.uce.moviles_parte1.logic.usercases.GetAllUsersFromTypiCode
import com.uce.moviles_parte1.logic.usercases.GetAllUsersUC
import com.uce.moviles_parte1.logic.usercases.SaveUserUC
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirstViewModel: ViewModel (){

    //Estoy permitiendo que solo la informacion salga
    //Aqui se va a usar live data, el tipo es el tipo de counter
    //Esto ya es logica(backend), es decir ya se separa
    val counterUI: LiveData<Int>
        get()= _counterUI

    private var _counterUI= MutableLiveData<Int>()

    val userRemote get()= _userRemote
    private var _userRemote= MutableLiveData<UserDtoRemote>()

    val listUsuarios:LiveData<List<UserDtoRemote>> get()=_listaUsuarios
    private var _listaUsuarios= MutableLiveData<List<UserDtoRemote>>()

    val typiUsers get()=_typiUsers
    private var _typiUsers= MutableLiveData<List<TypicodeUsersDtoItem>?>()

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
        saveUC: SaveUserUC
    ){

        viewModelScope.launch {
            val usnew=saveUC.saveUser(user)
            val usr=usnew.getOrNull()

            if(usr!=null){
                userRemote.value=usr
            }else {
                (UserDtoRemote("","Usuario no registrado",""))
            }


        }


    }

    fun getUsersTypi(){
        viewModelScope.launch {

            _typiUsers.value= GetAllUsersFromTypiCode().invoke()

        }
    }

    fun listarUsuarios(
        getAllUsersUC: GetAllUsersUC
    ){
        viewModelScope.launch {
            val usuarios=getAllUsersUC.invoke().getOrNull()
            if(usuarios!=null){
                _listaUsuarios.value=usuarios
            }else{
                _listaUsuarios.value= listOf()
            }

        }

    }

}