package com.proyecto.lab_tem1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity(name = "eventos")
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nombre;
    private int fecha;
    private int hora_iniciol;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNobre() {return nombre;}

    public void setNobre(String nombre) {this.nombre = nombre;}

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public int getHora_iniciol() {
        return hora_iniciol;
    }

    public void setHora_iniciol(int hora_iniciol) {
        this.hora_iniciol = hora_iniciol;
    }
}
