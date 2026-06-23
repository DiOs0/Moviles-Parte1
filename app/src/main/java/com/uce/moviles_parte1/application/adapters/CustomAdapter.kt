package com.uce.moviles_parte1.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uce.moviles_parte1.R
import com.uce.moviles_parte1.databinding.MySpinnerLayoutBinding
import com.uce.moviles_parte1.data.local.dto.Empresas

private val diffUtil = object : DiffUtil.ItemCallback<Empresas>() {
    override fun areItemsTheSame(oldItem: Empresas, newItem: Empresas): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Empresas, newItem: Empresas): Boolean = oldItem == newItem
}

class CustomAdapter(
    var onClick:(Empresas) -> Unit,
    var onDelete:(Empresas)->Unit


):
    ListAdapter<Empresas, CustomAdapter.CustomViewHolder>(diffUtil) {

    var lista: MutableList<Empresas> = ArrayList<Empresas>()


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
        holder.render(lista[position],onClick, onDelete)
    }

    override fun getItemCount()=lista.size

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){

        private var localBinding: MySpinnerLayoutBinding= MySpinnerLayoutBinding.bind(view)

        //El render manda cada elemento, no envia directamente
        fun render(item: Empresas,onClick:(Empresas) -> Unit,
                   onDelete:(Empresas)->Unit
                   ){
            localBinding.textEmpresa.setText(item.name)
            Picasso.get().load(item.image)
                .into(localBinding.imgEmpresa)

            //Aqui se esta mandando una empresa para que le vaya asignando a cada una
            localBinding.imgEmpresa.setOnClickListener {
                onClick(item)
            }
            localBinding.textEmpresa.setOnClickListener {
                onDelete(item)
            }
        }

    }


}