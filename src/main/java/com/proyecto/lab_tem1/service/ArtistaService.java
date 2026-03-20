package com.proyecto.lab_tem1.service;

import com.proyecto.lab_tem1.dto.ArtistaDTO;
import com.proyecto.lab_tem1.entity.Artista;
import com.proyecto.lab_tem1.repository.ArtistaRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public List<ArtistaDTO> findAll() {
        List<ArtistaDTO> lista = new ArrayList<>();
        List<Artista> artistas = artistaRepository.findAll();
        for (Artista a : artistas) {
            ArtistaDTO dto = new ArtistaDTO();
            dto.setId(a.getId());
            dto.setNombre_artistico(a.getNombre_artistico());
            dto.setGenero(a.getGenero());
            lista.add(dto);
        }
        return lista;
    }

    public ArtistaDTO save(ArtistaDTO artistaDTO) {
        Artista artista = new Artista();
        artista.setNombre_artistico(artistaDTO.getNombre_artistico());
        artista.setGenero(artistaDTO.getGenero());

        Artista artistaGuardado = artistaRepository.save(artista);
        artistaDTO.setId(artistaGuardado.getId());
        return artistaDTO;
    }

    public ArtistaDTO findById(Long id) {
        Artista a = artistaRepository.findById(id).orElseThrow();
        ArtistaDTO dto = new ArtistaDTO();
        dto.setId(a.getId());
        dto.setNombre_artistico(a.getNombre_artistico());
        dto.setGenero(a.getGenero());
        return dto;
    }
}