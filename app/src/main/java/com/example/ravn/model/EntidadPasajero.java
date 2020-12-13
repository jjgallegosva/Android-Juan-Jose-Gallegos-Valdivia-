package com.example.ravn.model;


import java.io.Serializable;
import java.util.ArrayList;

public class EntidadPasajero implements Serializable {
    private ArrayList<String> informacion;
    private ArrayList<String> vehicle;
    private String titulo;
    private String contenido;
    public EntidadPasajero(ArrayList<String> informacion,ArrayList<String>vehicle, String titulo, String contenido){
        this.informacion = informacion;
        this.titulo=titulo;
        this.contenido=contenido;
        this.vehicle=vehicle;
    }
    public ArrayList<String> getInformacion(){ return informacion;}
    public String getTitulo(){return titulo;}
    public String getContenido(){return contenido;}
    public ArrayList<String> getVehicle(){return vehicle;}
}
