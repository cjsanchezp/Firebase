package com.crisape.mifirebase

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPersonalizado(listaPersonas:List<Persona>):RecyclerView.Adapter<AdaptadorPersonalizado.MiViewHolder>() {

    private var listaPersonas:List<Persona> = listaPersonas

    class MiViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val filaDNI:TextView = view.findViewById(R.id.filaDNI)
        val filaNombres:TextView = view.findViewById(R.id.filaNombres)
        val filaCorreo:TextView = view.findViewById(R.id.filaCorreo)
        val filaItem:ConstraintLayout = view.findViewById(R.id.filaItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): AdaptadorPersonalizado.MiViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.fila,parent,false)
        return MiViewHolder(vista)
    }

    override fun onBindViewHolder(holder: AdaptadorPersonalizado.MiViewHolder, position: Int) {
        val item = listaPersonas[position]
        holder.filaDNI.text = item.dni
        holder.filaNombres.text = item.nombres
        holder.filaCorreo.text = item.correo
        holder.filaItem.setOnClickListener {v ->
            //Toast.makeText(v.context,"Hiciste Click", Toast.LENGTH_SHORT).show()
            val intent = Intent(v.context, DetalleActivity::class.java)
            intent.putExtra("key", item.id)
            v.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }
}