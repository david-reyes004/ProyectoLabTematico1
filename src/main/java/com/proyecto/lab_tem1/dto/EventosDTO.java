package com.proyecto.lab_tem1.dto;

public class EventosDTO {
    private long id; // Deja solo esta
    // BORRA la línea que decía: private int id;
    private int fecha;
    private int hora_inicio;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public int getFecha() { return fecha; }
    public void setFecha(int fecha) { this.fecha = fecha; }

    public int getHora_inicio() { return hora_inicio; }
    public void setHora_inicio(int hora_inicio) { this.hora_inicio = hora_inicio; }
}