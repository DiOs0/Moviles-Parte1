package com.uce.moviles_parte1.application.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.uce.moviles_parte1.R
import com.uce.moviles_parte1.databinding.FragmentFirstFramentBinding
import com.uce.moviles_parte1.dto.remote.dto.UserDtoRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstFramentBinding
    private var db = Firebase.firestore



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstFramentBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariables()
        initListeners()
    }



    private fun initListeners(){

        binding.btnRegresar.setOnClickListener {
            val user= UserDtoRemote(
                "",
                binding.nameUser.text.toString(),
                binding.lastnameUser.text.toString()
            )
            runBlocking {
                //Esto va a estar en ejecucion mientras el fragment este vivo
                //Comienza con un hilo principal
                lifecycleScope.launch  (Dispatchers.Main) {
                    //Aqui se desvia a otro hilo secundario
                    val usnew = withContext(Dispatchers.IO){
                        saveUser(user)
                    }
                    if(usnew.getOrNull() != null){
                        Snackbar.make(binding.nameUser,"Usuario guardado correctamente", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }
    }

    private suspend fun saveUser(user: UserDtoRemote): Result<UserDtoRemote>{
        var resp= db.collection("users")
            .add(user)
            .await()
            .runCatching {
                user
            }
        return resp
    }

    private fun initVariables() {
        db= Firebase.firestore
    }


}