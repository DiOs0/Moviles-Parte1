package com.uce.moviles_parte1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.uce.moviles_parte1.databinding.ActivityPrincipalBinding

class Principal : AppCompatActivity() {


    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initListeners()

        }


    private fun initVariables(){
        intent.extras.let {
            var saludo= it?.get("xx1")

            Snackbar.make(binding.urlText,
                saludo.toString(),
                Snackbar.LENGTH_LONG
                ).show()
        }
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
    }
}