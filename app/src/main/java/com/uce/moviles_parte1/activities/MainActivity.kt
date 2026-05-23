package com.uce.moviles_parte1.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.uce.moviles_parte1.R
import com.uce.moviles_parte1.databinding.ActivityFragmentBinding
import com.uce.moviles_parte1.fragments.FirstFrament
import com.uce.moviles_parte1.fragments.FragmentDos
import java.security.Principal

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()

    }

    fun initListeners(){
        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.mn_home -> {

                    supportFragmentManager.commit()
                    {
                        val f1 = FirstFrament()
                        replace(R.id.frameLayout, f1)
                        //addToBackStack(null)
                    }
                    true }

                R.id.mn_pag1 -> {

                    supportFragmentManager.commit {

                        val f2 = FragmentDos()
                        replace(R.id.frameLayout, f2)
                        //addToBackStack(null)
                    }

                    true }

                R.id.mn_pag2 -> {

                    MaterialAlertDialogBuilder(this)
                        .setTitle("Cerrar sesion")
                        .setMessage("¿Esta seguro de salir de la aplicacion?")
                        .setCancelable(true)
                        .setPositiveButton("Si"){
                                dialog , id ->
                            val intent = Intent(this, Principal::class.java)
                            startActivity(intent)
                        }
                        .setNegativeButton("No"){
                                dialog, id -> dialog.dismiss()
                        }.setNeutralButton("Cancelar"){
                                dialog, id -> dialog.dismiss()
                        }
                        .show()
                    true }
                else -> false
            }


        }
    }
}