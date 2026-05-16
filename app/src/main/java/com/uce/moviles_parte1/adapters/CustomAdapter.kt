package com.uce.moviles_parte1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.uce.moviles_parte1.R
import com.uce.moviles_parte1.databinding.MySpinnerLayoutBinding
import com.uce.moviles_parte1.dto.Empresas

class CustomAdapter(var lista:List<Empresas>): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        var inflater= LayoutInflater.from(parent.context)
        return CustomViewHolder(
            inflater.inflate(
                R.layout.my_spinner_layout,
                parent,
                false
            )

        )
    }

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int
    ) {
        holder.render(lista[position])
    }

    override fun getItemCount()=lista.size

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){

        private var localBinding: MySpinnerLayoutBinding= MySpinnerLayoutBinding.bind(view)

        fun render(item: Empresas){
            localBinding.textEmpresa.setText(item.name)
            localBinding.imgEmpresa.setImageURI(item.image.toUri())

        }

    }


}