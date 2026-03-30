package com.proyecto.lab_tem1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import com.proyecto.lab_tem1.entity.Eventos;
@Entity
@Table(name = "boletos")
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boletos")
    private long id;

    private String nombre;
    private double precio;
    private String compra;

    @ManyToOne
    @JoinColumn(name = "eventos_id_eventos")
    @JsonIgnoreProperties({"boletos", "hibernateLazyInitializer"})
    private Eventos evento;

    @ManyToOne
    @JoinColumn(name = "usuarios_id_usuarios")
    @JsonIgnoreProperties({"boletos", "password", "hibernateLazyInitializer"})
    private Usuario usuario;

    // --- Getters y Setters existentes ---
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getCompra() { return compra; }
    public void setCompra(String compra) { this.compra = compra; }

    public Eventos getEvento() { return evento; }
    public void setEvento(Eventos evento) { this.evento = evento; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}