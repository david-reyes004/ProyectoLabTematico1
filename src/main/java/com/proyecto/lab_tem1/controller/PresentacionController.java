package com.proyecto.lab_tem1.controller;

import com.proyecto.lab_tem1.entity.Presentacion;
import com.proyecto.lab_tem1.service.PresentacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presentaciones")
public class PresentacionController {

    @Autowired
    private PresentacionService presentacionService;

    @GetMapping
    public List<Presentacion> listarPresentaciones() {
        return presentacionService.obtenerTodas();
    }

    @PostMapping
    public Presentacion crearPresentacion(@RequestBody Presentacion presentacion) {
        return presentacionService.guardar(presentacion);
    }
}