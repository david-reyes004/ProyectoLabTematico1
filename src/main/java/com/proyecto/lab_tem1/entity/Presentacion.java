package com.proyecto.lab_tem1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "presentaciones")
public class Presentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presentaciones")
    private long id;

    private String hora_presentacion;

    @ManyToOne
    @JoinColumn(name = "artistas_id_artistas")
    @JsonIgnoreProperties({"presentaciones", "hibernateLazyInitializer"})
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "eventos_id_eventos")
    @JsonIgnoreProperties({"presentaciones", "boletos", "hibernateLazyInitializer"})
    private Eventos evento;

    @ManyToOne
    @JoinColumn(name = "escenarios_id_escenarios")
    @JsonIgnoreProperties({"presentaciones", "hibernateLazyInitializer"})
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