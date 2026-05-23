package com.uce.moviles_parte1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uce.moviles_parte1.databinding.FragmentFirstFramentBinding


class FirstFrament : Fragment() {

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


}