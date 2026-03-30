package com.proyecto.lab_tem1.service;

import com.proyecto.lab_tem1.dto.EscenarioDTO;
import com.proyecto.lab_tem1.entity.Escenario;
import com.proyecto.lab_tem1.repository.EscenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EscenarioService {

    @Autowired
    private EscenarioRepository escenarioRepository;


    public List<EscenarioDTO> findAll() {
        return escenarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public EscenarioDTO findById(Long id) {
        Escenario escenario = escenarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Escenario no encontrado con id: " + id));
        return toDTO(escenario);
    }


    public EscenarioDTO save(EscenarioDTO dto) {
        Escenario escenario = toEntity(dto);
        Escenario guardado = escenarioRepository.save(escenario);
        return toDTO(guardado);
    }


    public void deleteById(Long id) {
        escenarioRepository.deleteById(id);
    }



    private EscenarioDTO toDTO(Escenario escenario) {
        EscenarioDTO dto = new EscenarioDTO();
        dto.setId_escenario(escenario.getId().toString());
        dto.setCapacidad(escenario.getCapacidad());
        dto.setUbicacion(escenario.getUbicacion());
        dto.setId_escenario(String.valueOf(escenario.getEventosIdEventos()));
        return dto;
    }

    private Escenario toEntity(EscenarioDTO dto) {
        Escenario escenario = new Escenario();
        escenario.setCapacidad(dto.getCapacidad());
        escenario.setUbicacion(dto.getUbicacion());
        escenario.setEventosIdEventos(Long.parseLong(dto.getId_escenario()));
        return escenario;
    }
}