package com.uce.moviles_parte1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uce.moviles_parte1.databinding.ActivityLoginDeberBinding

class LoginDeber : AppCompatActivity() {

    //Esto es para que cada objeto de esta clase que tenga el id se convierta en objeto directamente
    lateinit var binding: ActivityLoginDeberBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginDeberBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {

            var msg=""
            if(binding.txtUsername.text.toString() == "Admin" && binding.txtPassword.text.toString() =="root") msg= "Usuario ingreso correctamente"
            else msg="No pudo ingresar"

            binding.btnSignUp.setOnClickListener {
                Toast.makeText(this,msg,
                    Toast.LENGTH_LONG).show()
            }
        }



        }
    }
