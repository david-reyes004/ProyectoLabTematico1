package com.proyecto.lab_tem1.service;

import com.proyecto.lab_tem1.dto.BoletoDTO;
import com.proyecto.lab_tem1.entity.Boleto;
import com.proyecto.lab_tem1.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    public List<Boleto> obtenerTodos() {
        return boletoRepository.findAll();
    }

    public BoletoDTO guardar(Boleto boleto) {
        boletoRepository.save(boleto);

        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setId(boleto.getId());
        boletoDTO.setNombre(boleto.getNombre());
        boletoDTO.setPrecio(boleto.getPrecio());
        boletoDTO.setCompra(boleto.getCompra());

        return boletoDTO;
    }
}