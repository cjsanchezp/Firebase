package com.crisape.mifirebase

class Persona(id:String,dni:String, nombres:String, correo:String) {

    constructor(dni: String, nombres: String, correo: String):this ("", dni,nombres,correo)

    var id:String = id
    var dni:String = dni
    var nombres:String = nombres
    var correo:String = correo
}

