package com.proyecto.lab_tem1.controller;


import org.springframework.web.bind.annotation.*;
import com.proyecto.lab_tem1.service.EventosService;
import com.proyecto.lab_tem1.dto.EventosDTO;

import java.util.List;

@RestController
@CrossOrigin

public class EventosController {

    private EventosService eventosService;

    public EventosController(EventosService eventosService){
        this.eventosService=eventosService;
    }

    @GetMapping("/eventos")

    public List<EventosDTO> eventos(){
        return eventosService.findAll();
    }

    @PostMapping("/eventos")

    public EventosDTO save(@RequestBody EventosDTO eventosDTO){
    return eventosService.save(eventosDTO);
    }
    @GetMapping("/eventos/{id}")
    public EventosDTO eventos(@PathVariable Long id){
        return eventosService.findById(id);
    }
}
