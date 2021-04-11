package com.example.gestionaudiovisualtv.model;

import java.util.Date;

public class Proyecto {

    private int id;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private String descripcion;
    private int presupuesto;
    private Boolean esActivo;


    public Proyecto(String nombre){

    }

    public Proyecto(int id, String nombre, Date fechaInicio, Date fechaFin, String descripcion, int presupuesto, Boolean esActivo) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.presupuesto = presupuesto;
        this.esActivo = esActivo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public Boolean getEsActivo() {
        return esActivo;
    }
}
