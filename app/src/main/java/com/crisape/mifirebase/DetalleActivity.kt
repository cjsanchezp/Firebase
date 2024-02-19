package com.crisape.mifirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class DetalleActivity : AppCompatActivity() {

    private lateinit var detNombres:TextView
    private lateinit var detDNI:TextView
    private lateinit var detCorreo:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        asignarReferencia()
        recibirCodigo()
    }
    private fun asignarReferencia(){
        detNombres = findViewById(R.id.detNombres)
        detDNI = findViewById(R.id.detDNI)
        detCorreo = findViewById(R.id.detCorreo)
    }

    private fun recibirCodigo(){
        val key = intent.getStringExtra("key")
        //Toast.makeText(this,key,Toast.LENGTH_SHORT).show()
        val db = Firebase.database
        val referencia = db.getReference("personas").child(key.toString())

        referencia.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val persona:Persona1? = snapshot.getValue(Persona1::class.java)
                if (persona != null){
                    detNombres.text = persona.nombres
                    detDNI.text = persona.dni
                    detCorreo.text = persona.correo
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}