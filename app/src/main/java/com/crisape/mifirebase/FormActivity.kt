package com.crisape.mifirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.Firebase
import com.google.firebase.database.database

class FormActivity : AppCompatActivity() {

    private lateinit var txtDNI:EditText
    private lateinit var txtNombres:EditText
    private lateinit var txtCorreo:EditText
    private lateinit var btnGuardar:Button

    private val db = Firebase.database
    private val reference = db.getReference("personas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        asignarReferencias()
    }

    private fun asignarReferencias(){
        txtDNI = findViewById(R.id.txtDNI)
        txtNombres = findViewById(R.id.txtNombres)
        txtCorreo = findViewById(R.id.txtCorreo)
        btnGuardar = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            guardar()
        }
    }

    private fun guardar(){
        val dni = txtDNI.text.toString()
        val nombres = txtNombres.text.toString()
        val correo = txtCorreo.text.toString()

        val persona = Persona(dni,nombres,correo)
        reference.child(reference.push().key.toString()).setValue(persona)
        finish()
    }
}