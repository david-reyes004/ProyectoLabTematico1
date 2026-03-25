package com.proyecto.lab_tem1.dto;

import java.sql.Time;
import java.util.Date;

public class EventosDTO {
    private long id;
    private String nombre;
    private Date fecha;
    private Time horaInicio;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Time getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Time horaInicio) { this.horaInicio = horaInicio; }
}