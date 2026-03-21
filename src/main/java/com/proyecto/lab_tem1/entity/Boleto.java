package com.proyecto.lab_tem1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "boletos")
public class Boleto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boletos")
    private long id;

    private double precio;
    private String compra;

    // Relación con Eventos
    @ManyToOne
    @JoinColumn(name = "eventos_id_eventos")
    private Eventos evento;

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "usuarios_id_usuarios")
    private Usuario usuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

    public Eventos getEvento() {
        return evento;
    }

    public void setEvento(Eventos evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}