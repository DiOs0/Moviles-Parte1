package com.uce.moviles_parte1

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.uce.moviles_parte1.adapters.CustomAdapter
import com.uce.moviles_parte1.databinding.ActivityPrincipalBinding
import com.uce.moviles_parte1.dto.Empresas

class Principal : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    private lateinit var binding: ActivityPrincipalBinding
    private lateinit var adapterRecyclerView: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initListeners()

        }


    override fun onDestroy() {
        super.onDestroy()

    }


    private fun initVariables(){
        intent.extras.let {
            var saludo= it?.get("xx1")

            Snackbar.make(binding.urlText,
                saludo.toString(),
                Snackbar.LENGTH_LONG
                ).show()
        }

        var options =listOf<String>("Youtube","Google","Facebook","Apple","GoyGram")
        var optionsEmpresas=listOf<Empresas>(
            Empresas("Youtube","https://www.imprentaonline.net/blog/wp-content/uploads/logotipo-youtube-2015.png","https://www.youtube.com/"),
            Empresas("Google","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlh1Kyfo9hJplmkiOKcHD9XcpUvlJaZrh5ZA&s","https://www.google.com/"),
            Empresas("Instagram","https://i.ytimg.com/vi/Hg469wSrZhI/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLDt_wnd3dLlZ5yjXITG7_wUI8c3jw","https://www.instagram.com/")

        )

        //Adaptador/Intermediario
        var adapter= ArrayAdapter(this,R.layout.my_spinner_layout,options)


//        binding.spinnerUrls.adapter=adapter
//        binding.spinnerUrls.onItemSelectedListener=this

        //Recycler View
        var adapterRecyclerView= CustomAdapter(
            {getName(it)},
            {deleteEmpresa(it)}
        )
        binding.RvUrls.adapter= adapterRecyclerView
        binding.RvUrls.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,
            true
            )

        adapterRecyclerView.lista=optionsEmpresas as MutableList<Empresas>
        adapterRecyclerView.notifyDataSetChanged()



//            binding.RvUrls.layoutManager= GridLayoutManager(this,2)

    }


    fun getName(emp: Empresas){
//        Snackbar.make(binding.RvUrls,
//            emp.name,
//            Snackbar.LENGTH_LONG
//            ).show()

        val i =Intent(Intent.ACTION_WEB_SEARCH)
        i.putExtra(SearchManager.QUERY,emp.name)
        startActivity(i)

    }

    fun deleteEmpresa(emp: Empresas){
        var newEmpresas=adapterRecyclerView.lista.minus(emp)
        adapterRecyclerView.lista=newEmpresas as MutableList<Empresas>
        adapterRecyclerView.notifyDataSetChanged()

    }


    private fun initListeners(){
        binding.urlBtn.setOnClickListener {

            val url= binding.urlText.text.toString()

            val i =Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)


            // Create a Uri a partir de coordenadas
            //val gmmIntentUri = Uri.parse("geo:-0.2016041,-78.5098424")
            //val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            //mapIntent.setPackage("com.google.android.apps.maps")
            //startActivity(mapIntent)

        }


        //Este es un reemplazo del toast (es mas interactivo)
        binding.logoutBtn.setOnClickListener {

            val dialog = MaterialAlertDialogBuilder(this)
                .setTitle("Log Out")
                .setMessage("Esta seguro de salir de la aplicacion")
                .setCancelable(true)
                .setPositiveButton("Si"){dialog,id->
                    val intent = Intent(this, Login2_Constraint::class.java)
                    startActivity(intent)

                }
                .setNegativeButton("No"){dialog,id->
                    dialog.cancel()

                }
                .setNeutralButton("Cancelar"){dialog,id->
                    dialog.dismiss()

                }
                .show()

            //val intent = Intent(this, Login2_Constraint::class.java)
            //startActivity(intent)
        }
    }

    override fun onItemSelected(parent:AdapterView<*>?,view:View,position:Int,id:Long) {
        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle("Posicion")
            .setMessage("La posicion es:" + position)
            .setCancelable(true)
            .show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}