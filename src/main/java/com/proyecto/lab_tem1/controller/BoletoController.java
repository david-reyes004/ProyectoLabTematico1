package com.proyecto.lab_tem1.controller;

import com.proyecto.lab_tem1.entity.Boleto;
import com.proyecto.lab_tem1.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boletos")
public class BoletoController {

    @Autowired
    private BoletoService boletoService;

    @GetMapping
    public List<Boleto> listarBoletos() {
        return boletoService.obtenerTodos();
    }

    @PostMapping
    public Boleto crearBoleto(@RequestBody Boleto boleto) {
        return boletoService.guardar(boleto);
    }
}