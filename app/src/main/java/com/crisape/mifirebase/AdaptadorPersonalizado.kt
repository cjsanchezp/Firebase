package com.crisape.mifirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPersonalizado(listaPersonas:List<Persona>):RecyclerView.Adapter<AdaptadorPersonalizado.MiViewHolder>() {

    private var listaPersonas:List<Persona> = listaPersonas

    class MiViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val filaDNI:EditText = view.findViewById(R.id.filaDNI)
        val filaNombres:EditText = view.findViewById(R.id.filaNombres)
        val filaCorreo:EditText = view.findViewById(R.id.filaCorreo)

    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): AdaptadorPersonalizado.MiViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.fila,parent,false)
        return MiViewHolder(vista)
    }

    override fun onBindViewHolder(holder: AdaptadorPersonalizado.MiViewHolder, position: Int) {
        val item = listaPersonas[position]
        //holder.filaDNI.text = item.dni.toString()
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }
}