package com.proyecto.lab_tem1.service; /*Quedó igual*/

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

    public Boleto guardar(Boleto boleto) {
        return boletoRepository.save(boleto);
    }
}