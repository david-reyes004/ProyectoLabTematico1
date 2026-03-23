
package com.proyecto.lab_tem1.service;

import com.proyecto.lab_tem1.dto.EventosDTO;
import org.springframework.stereotype.Service;
import com.proyecto.lab_tem1.entity.Eventos;
import com.proyecto.lab_tem1.repository.EventosRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventosService {

    private EventosRepository eventosRepository;

    public EventosService(EventosRepository eventosRepository){
        this.eventosRepository = eventosRepository;
    }

    public List<EventosDTO> findAll(){
        List<EventosDTO> lista = new ArrayList<>();
        List<Eventos> eventos = eventosRepository.findAll();

        // Cambié 'eventos: Eventos' por 'e : eventos' para evitar conflicto de nombres
        for (Eventos e : eventos){
            EventosDTO eventosDTO = new EventosDTO();

            eventosDTO.setId(e.getId());
            eventosDTO.setNombre(e.getNombre());
            eventosDTO.setFecha(e.getFecha());
            eventosDTO.setHora_inicio(e.getHora_inicio());

            lista.add(eventosDTO);
        }
        return lista;
    }

    public EventosDTO save(EventosDTO eventosDTO){
        Eventos eventosEntity = new Eventos();
        eventosEntity.setId(eventosDTO.getId());
        eventosEntity.setNombre(eventosDTO.getNombre());
        eventosEntity.setFecha(eventosDTO.getFecha());
        eventosEntity.setHora_inicio(eventosDTO.getHora_inicio());

        Eventos eventos2 = eventosRepository.save(eventosEntity);
        eventosDTO.setId(eventos2.getId());
        return eventosDTO;
    }

    // Corregido a findById (con 'd' minúscula) para que coincida con el repositorio
    public EventosDTO findById(Long id){
        Eventos eventos = eventosRepository.findById(id).get();
        EventosDTO eventosDTO = new EventosDTO();

        eventosDTO.setId(eventos.getId());
        eventosDTO.setNombre(eventos.getNombre());
        eventosDTO.setFecha(eventos.getFecha());
        eventosDTO.setHora_inicio(eventos.getHora_inicio());

        return eventosDTO;
    }
}