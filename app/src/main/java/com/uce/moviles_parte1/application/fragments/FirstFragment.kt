package com.uce.moviles_parte1.application.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.cloudinary.android.MediaManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.uce.moviles_parte1.application.viewmodels.FirstViewModel
import com.uce.moviles_parte1.databinding.FragmentFirstFramentBinding
import com.uce.moviles_parte1.data.remote.dto.UserDtoRemote
import com.uce.moviles_parte1.logic.usercases.GetAllUsersUC
import com.uce.moviles_parte1.logic.usercases.SaveUserUC
import com.uce.moviles_parte1.repositories.CloudinaryRepository
import com.uce.moviles_parte1.repositories.UserRepository
import com.uce.moviles_parte1.repositories.connection.local.LocalDataBase
import com.uce.moviles_parte1.repositories.connection.remote.UserRemoteImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream


class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstFramentBinding
    private var db = Firebase.firestore

    private lateinit var dblocal: LocalDataBase

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
                "11",
                binding.nameUser.text.toString(),
                binding.lastnameUser.text.toString(),
                null
            )

            lifecycleScope.launch(Dispatchers.Main) {
                firstVM.guardarUsuario(
                    user,
                    SaveUserUC(
                        UserRepository(
                            UserRemoteImpl(db),dblocal
                        ),UploadImageInCloudinary(requireContext())
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
        binding.btnApi.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {

                firstVM.getUsersTypi()
            }

        }

        binding.btnSubir.setOnClickListener {
            viewGalery.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )

        }
    }

    private val NOMBRE_ARCHIVO_CACHE= "temp_upload_image.jpg"

    private val viewGalery= registerForActivityResult(

        ActivityResultContracts.PickVisualMedia()

    )
    {
            uri->
        if(uri!=null){
            //Guardar en el archivo temporal
            val valid= guardarArchivoTemporal(uri)
            //if(se guardo en el archivo temporal??)
            if(valid){
                Toast.makeText(requireContext()
                    , "El archivo esta listo para ser subido"
                    , Toast.LENGTH_SHORT).show()

                subirImagen()
            }
            else{
                Toast.makeText(requireContext()
                    , "Ocurrio un error"
                    , Toast.LENGTH_SHORT).show()
            }

        }



    }

    private fun guardarArchivoTemporal(uri: Uri): Boolean {
        return try{
            val contentResolver=requireContext().contentResolver
            val temporalFile = File(requireContext().cacheDir,"img_temp.jpg")
            val inputStream=contentResolver.openInputStream(uri)?:return false
            val outputStream= FileOutputStream(temporalFile)

            inputStream.copyTo(outputStream)

            inputStream.close()
            outputStream.close()

            true
        }
        catch (ex: Exception){
            Log.e("UCE",ex.message.toString())
            false
        }
    }

    private fun subirImagen(){
        val archivoCache= File(requireContext().cacheDir,"img_temp.jpg")
        CloudinaryRepository.subirImagenFirmada(archivoCache){
            esExitoso,resultado->

            lifecycleScope.launch (Dispatchers.IO){
                val resultText = if(esExitoso){
                    "La imagen se subio correctamente en ${resultado}"

                }else{
                    "Ocurrio un error ${resultado}"
                }

                withContext(Dispatchers.Main){
                    Toast.makeText(
                        requireContext(),
                        resultText,
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }


        }
    }


    private fun initObservers(){


        firstVM.typiUsers.observe(viewLifecycleOwner){
            it?.forEach {user->
                Log.d("ITEMS",user.name)
            }
        }


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

        val config = mapOf(
            "cloud_name" to "dqsor8lhk",
            "api_key" to "868664956411584",
            "api_secret" to " nadota"

        )


        MediaManager.init(
            requireContext(),
            config
        )

        dblocal = Room.databaseBuilder(
            requireContext(),
            LocalDataBase::class.java,"databe"
        ).build()


    }


}