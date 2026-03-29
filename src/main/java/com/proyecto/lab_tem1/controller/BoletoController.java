package com.proyecto.lab_tem1.controller;

import com.proyecto.lab_tem1.dto.BoletoDTO;
import com.proyecto.lab_tem1.dto.UserDTO;
import com.proyecto.lab_tem1.entity.Boleto;
import com.proyecto.lab_tem1.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoletoController {

    @Autowired
    private BoletoService boletoService;

    @GetMapping("/boletos")
    public List<Boleto> listarBoletos() {return boletoService.obtenerTodos();}


    @PostMapping("/boletos")
    public BoletoDTO save(@RequestBody BoletoDTO boletoDTO) {
        Boleto boleto = new Boleto();
        boleto.setNombre(boletoDTO.getNombre());
        boleto.setPrecio(boletoDTO.getPrecio());
        boleto.setCompra(boletoDTO.getCompra());
        return boletoService.guardar(boleto);
    }

}