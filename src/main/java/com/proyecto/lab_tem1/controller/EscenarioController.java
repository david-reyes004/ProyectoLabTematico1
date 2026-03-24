package com.proyecto.lab_tem1.controller;

import com.proyecto.lab_tem1.dto.EscenarioDTO;
import com.proyecto.lab_tem1.service.EscenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escenarios")
public class EscenarioController {

    @Autowired
    private EscenarioService escenarioService;


    @GetMapping
    public List<EscenarioDTO> getEscenarios() {
        return escenarioService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<EscenarioDTO> getEscenario(@PathVariable Long id) {
        EscenarioDTO dto = escenarioService.findById(id);
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<EscenarioDTO> save(@RequestBody EscenarioDTO dto) {
        EscenarioDTO guardado = escenarioService.save(dto);
        return ResponseEntity.ok(guardado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        escenarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}