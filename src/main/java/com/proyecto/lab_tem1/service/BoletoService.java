package com.proyecto.lab_tem1.service;

import com.proyecto.lab_tem1.dto.BoletoDTO;
import com.proyecto.lab_tem1.entity.Boleto;
import com.proyecto.lab_tem1.entity.Presentacion;
import com.proyecto.lab_tem1.repository.BoletoRepository;
import com.proyecto.lab_tem1.repository.PresentacionRepository; // Asegúrate de tener este import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired // IMPORTANTE: Agregamos el Autowired aquí para que no sea null
    private PresentacionRepository presentacionRepository;

    public List<Boleto> obtenerTodos() {
        return boletoRepository.findAll();
    }

    // Modificamos el método guardar para que use el DTO y busque la presentación
    public BoletoDTO guardar(BoletoDTO boletoDTO) {
        // 1. Buscamos la presentación por el ID que viene del frontend
        Presentacion presentacion = presentacionRepository.findById(boletoDTO.getPresentacionId())
                .orElseThrow(() -> new RuntimeException("No se encontró la presentación con ID: " + boletoDTO.getPresentacionId()));

        // 2. Creamos la entidad Boleto y le pasamos los datos
        Boleto boleto = new Boleto();
        boleto.setNombre(boletoDTO.getNombre());
        boleto.setPrecio(boletoDTO.getPrecio());
        boleto.setCompra("EXITOSA"); // O el estado que prefieras

        // 3. ASIGNAMOS LA PRESENTACIÓN (Aquí es donde se liga el ID y la Fecha)
        boleto.setPresentacion(presentacion);

        // 4. Guardamos en la base de datos
        boletoRepository.save(boleto);

        // 5. Actualizamos el DTO con el ID generado para devolverlo al frontend
        boletoDTO.setId(boleto.getId());

        return boletoDTO;
    }
}