package com.uce.moviles_parte1.application.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.uce.moviles_parte1.R
import com.uce.moviles_parte1.application.viewmodels.FirstViewModel
import com.uce.moviles_parte1.databinding.FragmentFirstFramentBinding
import com.uce.moviles_parte1.dto.remote.dto.UserDtoRemote
import com.uce.moviles_parte1.logic.usercases.GetAllUsersUC
import com.uce.moviles_parte1.logic.usercases.SaveUserUC
import com.uce.moviles_parte1.repositories.connection.UserRepository
import com.uce.moviles_parte1.repositories.connection.remote.UserRemoteImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstFramentBinding
    private var db = Firebase.firestore

    //Clase los delegados, es para atar caracteristicas extras a mi proyecto
    //La vista solo es consumidora
    private val firstVM by viewModels<FirstViewModel>()



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
        initObservers()
    }



    private fun initListeners(){

        binding.btnRegresar.setOnClickListener {
            val user= UserDtoRemote(
                "",
                binding.nameUser.text.toString(),
                binding.lastnameUser.text.toString()
            )

            lifecycleScope.launch (Dispatchers.Main ){
                firstVM.contador()

            }

            lifecycleScope.launch(Dispatchers.Main) {
                firstVM.guardarUsuario(
                    user,
                    SaveUserUC(
                        UserRepository(
                            UserRemoteImpl(db)
                        )
                    )
                )
                //Los observers se registran solo una vez
            }

            lifecycleScope.launch {
                firstVM.listarUsuarios(
                    GetAllUsersUC(
                        UserRepository(
                            UserRemoteImpl(db)
                        )

                    )
                )
            }






            //        //Esto va a estar en ejecucion mientras el fragment este vivo
//        //Comienza con un hilo principal
//        lifecycleScope.launch  (Dispatchers.Main) {
//
//            //Aqui se desvia a otro hilo secundario
//            val usnew = withContext(Dispatchers.IO){
//                saveUser(user)
//            }
//            if(usnew.getOrNull() != null){
//                Snackbar.make(binding.nameUser,"Usuario guardado correctamente", Snackbar.LENGTH_SHORT)
//                    .show()
//            }
//        }


        }
    }


    private fun initObservers(){


        //Comienzo a observarlo si existe un cambio y el cilco de vida es la del activity
        //Aqui simplemente esta actualizando la vista, lo que hace por detras es en el FirstViewModel
        firstVM.counterUI.observe(viewLifecycleOwner){
            binding.contadorTxt.text=it.toString()
        }

        firstVM.userRemote.observe(viewLifecycleOwner){
            Snackbar.make(binding.nameUser,
                it.name+" Registrado correctamente",
                Snackbar.LENGTH_SHORT)
                .show()
        }

        firstVM.listUsuarios.observe(viewLifecycleOwner){users->
            users.forEach{
                Log.d("TAG",it.toString())
            }

        }


    }

    private fun initVariables() {
        db= Firebase.firestore
    }


}