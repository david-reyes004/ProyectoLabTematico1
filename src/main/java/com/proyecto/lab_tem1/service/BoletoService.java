package com.proyecto.lab_tem1.service;

import com.proyecto.lab_tem1.dto.BoletoDTO;
import com.proyecto.lab_tem1.entity.Boleto;
import com.proyecto.lab_tem1.entity.Eventos;
import com.proyecto.lab_tem1.entity.Usuario;
import com.proyecto.lab_tem1.repository.BoletoRepository;
import com.proyecto.lab_tem1.repository.EventosRepository;
import com.proyecto.lab_tem1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class BoletoService {

    @Autowired
    private EventosRepository eventosRepository;

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private UserRepository userRepository;

    public BoletoDTO guardar(Boleto boleto, long eventoId, long usuarioId) {
        Eventos evento = eventosRepository.findById(eventoId).orElse(null);
        boleto.setEvento(evento);

        Usuario usuario = userRepository.findById(usuarioId).orElse(null);
        boleto.setUsuario(usuario);

        boletoRepository.save(boleto);

        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setId(boleto.getId());
        boletoDTO.setNombre(boleto.getNombre());
        boletoDTO.setPrecio(boleto.getPrecio());
        boletoDTO.setCompra(String.valueOf(boleto.getCompra()));
        boletoDTO.setEventoId(eventoId);
        boletoDTO.setUsuarioId(usuarioId);

        return boletoDTO;
    }

    public List<BoletoDTO> obtenerTodosDTO() {
        List<Boleto> boletos = boletoRepository.findAll();
        List<BoletoDTO> dtos = new ArrayList<>();

        for (Boleto b : boletos) {
            BoletoDTO dto = new BoletoDTO();
            dto.setId(b.getId());
            dto.setNombre(b.getNombre());
            dto.setPrecio(b.getPrecio());
            dto.setCompra(b.getCompra());
            dto.setEventoId(b.getEvento() != null ? b.getEvento().getId() : 0);
            dto.setUsuarioId(b.getUsuario() != null ? b.getUsuario().getId() : 0);
            dtos.add(dto);
        }
        return dtos;
    }
}