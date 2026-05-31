package com.uce.moviles_parte1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.uce.moviles_parte1.R
import com.uce.moviles_parte1.databinding.FragmentFirstFramentBinding


class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstFramentBinding


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

        initListeners()
    }

    private fun initListeners(){

        binding.btnRegresar.setOnClickListener {

            findNavController().navigate(R.id.action_firstFragment_to_secondfragment)
//            FirstFragmentDirections.actionFirstFragmentToListaFragment(0, "xxx")
        }
    }


}