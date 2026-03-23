package com.proyecto.lab_tem1.service;

import com.proyecto.lab_tem1.entity.Presentacion;
import com.proyecto.lab_tem1.repository.PresentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresentacionService {

    @Autowired
    private PresentacionRepository presentacionRepository;

    public List<Presentacion> obtenerTodas() {
        return presentacionRepository.findAll();
    }

    public Presentacion guardar(Presentacion presentacion) {
        return presentacionRepository.save(presentacion);
    }
}