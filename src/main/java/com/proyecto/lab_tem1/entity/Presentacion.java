package com.proyecto.lab_tem1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "presentaciones")
public class Presentacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presentaciones")
    private long id;

    private String hora_presentacion;

    // Relación con Artista
    @ManyToOne
    @JoinColumn(name = "artistas_id_artistas")
    private Artista artista;

    // Relación con Eventos
    @ManyToOne
    @JoinColumn(name = "eventos_id_eventos")
    private Eventos evento;

    // Relación con Escenario
    @ManyToOne
    @JoinColumn(name = "escenarios_id_escenarios")
    private Escenario escenario;

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

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Eventos getEvento() {
        return evento;
    }

    public void setEvento(Eventos evento) {
        this.evento = evento;
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }
}