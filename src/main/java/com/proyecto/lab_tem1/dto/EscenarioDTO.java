package com.proyecto.lab_tem1.dto;

public class EscenarioDTO {

    private Long id;
    private String capacidad;
    private String ubicacion;
    private String id_escenario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCapacidad() { return capacidad; }
    public void setCapacidad(String capacidad) { this.capacidad = capacidad; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getId_escenario() { return id_escenario; }
    public void setId_escenario(String id_escenario) { this.id_escenario = id_escenario; }
}