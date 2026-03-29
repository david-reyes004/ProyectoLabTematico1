package com.proyecto.lab_tem1.controller;

import com.proyecto.lab_tem1.dto.ArtistaDTO;
import com.proyecto.lab_tem1.service.ArtistaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping
    public List<ArtistaDTO> findAll() {
        return artistaService.findAll();
    }

    @PostMapping
    public ArtistaDTO save(@RequestBody ArtistaDTO artistaDTO) {
        return artistaService.save(artistaDTO);
    }

    @GetMapping("/{id}")
    public ArtistaDTO findById(@PathVariable Long id) {
        return artistaService.findById(id);
    }
}