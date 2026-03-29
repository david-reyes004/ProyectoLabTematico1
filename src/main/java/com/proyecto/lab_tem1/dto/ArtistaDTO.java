package com.proyecto.lab_tem1.dto;

public class ArtistaDTO {
    private long id;
    private String nombre_artistico;
    private String genero;

    // Getters y Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre_artistico() { return nombre_artistico; }
    public void setNombre_artistico(String nombre_artistico) { this.nombre_artistico = nombre_artistico; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}