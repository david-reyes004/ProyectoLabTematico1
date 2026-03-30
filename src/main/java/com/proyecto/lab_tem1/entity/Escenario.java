package com.proyecto.lab_tem1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "escenarios")
public class Escenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String capacidad;
    private String ubicacion;

    @Column(name = "eventos_id_eventos")
    private Long eventosIdEventos;

    public Escenario() {}

    public Escenario(String capacidad, String ubicacion, Long eventosIdEventos) {
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.eventosIdEventos = eventosIdEventos;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCapacidad() { return capacidad; }
    public void setCapacidad(String capacidad) { this.capacidad = capacidad; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public Long getEventosIdEventos() { return eventosIdEventos; }
    public void setEventosIdEventos(Long eventosIdEventos) { this.eventosIdEventos = eventosIdEventos; }
}