package com.example.ravn.model

import java.io.Serializable

public class PeopleStarWars(informacion: ArrayList<String>, vehicle: ArrayList<String>, titulo: String, contenido: String):Serializable {
    val informacion:ArrayList<String>
    val vehicle: ArrayList<String>
    val titulo: String
    val contenido: String
    init{
        this.informacion=informacion
        this.vehicle=vehicle
        this.titulo=titulo
        this.contenido=contenido
    }

    fun getInformacions(): ArrayList<String> {
        return informacion
    }

    fun getTitulos(): String {
        return titulo
    }

    fun getContenidos(): String {
        return contenido
    }

    fun getVehicles(): ArrayList<String> {
        return vehicle
    }

}