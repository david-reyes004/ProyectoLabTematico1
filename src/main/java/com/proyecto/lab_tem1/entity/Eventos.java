package com.proyecto.lab_tem1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

import java.sql.Time;
import java.util.Date;

@Entity(name = "eventos")
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nombre;
    private Date fecha;
    private Time horaInicio;
    private double precio;

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraInicio() { return horaInicio; }

    public void setHoraInicio(Time horaInicio) { this.horaInicio = horaInicio;}
}