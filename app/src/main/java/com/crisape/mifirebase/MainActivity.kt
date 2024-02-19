package com.crisape.mifirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {

    private lateinit var btnNuevo:FloatingActionButton
    private lateinit var rvPersonas:RecyclerView

    private val db = Firebase.database
    private val referencia = db.getReference("personas")

    private val listaPersonas:ArrayList<Persona> = ArrayList()
    private lateinit var messageListener: ValueEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        asignarReferencias()
        cargarDatos()

    }

    private fun cargarDatos(){
        listaPersonas.clear()
        configReciclerView(rvPersonas)
    }

    private fun configReciclerView(rvPersonas:RecyclerView){
        messageListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listaPersonas.clear()
                snapshot.children.forEach { item ->
                    val persona:Persona = Persona(
                        item.key.toString(),
                        item.child("dni").getValue().toString(),
                        item.child("nombres").getValue().toString(),
                        item.child("correo").getValue().toString()

                    )
                    persona.let { listaPersonas.add(it) }
                }
                Log.d("==>", listaPersonas.size.toString())
                rvPersonas.layoutManager = LinearLayoutManager(this@MainActivity)
                rvPersonas.adapter = AdaptadorPersonalizado(listaPersonas)

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("==>", error.message)
            }

        }
        referencia.addValueEventListener(messageListener)
    }

    private fun asignarReferencias(){
        btnNuevo = findViewById(R.id.btnNuevo)
        btnNuevo.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        rvPersonas = findViewById(R.id.rvPersonas)
    }
}