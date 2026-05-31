package com.uce.moviles_parte1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uce.moviles_parte1.R
import com.uce.moviles_parte1.databinding.FragmentDosBinding

class FragmentDos : Fragment() {

    lateinit var binding: FragmentDosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDosBinding.inflate(
            layoutInflater,
            container,
            false
        )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners(){

        binding.btnGotolistar.setOnClickListener {
            findNavController().navigate(R.id.action_listaFragment_to_firstFragment)
        }
    }


}