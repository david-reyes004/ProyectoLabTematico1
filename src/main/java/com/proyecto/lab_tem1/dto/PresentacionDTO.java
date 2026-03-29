package com.proyecto.lab_tem1.dto;

public class PresentacionDTO {
    private long id;
    private String hora_presentacion;
    private long artistaId;
    private long eventoId;
    private long escenarioId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHora_presentacion() {
        return hora_presentacion;
    }

    public void setHora_presentacion(String hora_presentacion) {
        this.hora_presentacion = hora_presentacion;
    }

    public long getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(long artistaId) {
        this.artistaId = artistaId;
    }

    public long getEventoId() {
        return eventoId;
    }

    public void setEventoId(long eventoId) {
        this.eventoId = eventoId;
    }

    public long getEscenarioId() {
        return escenarioId;
    }

    public void setEscenarioId(long escenarioId) {
        this.escenarioId = escenarioId;
    }
}