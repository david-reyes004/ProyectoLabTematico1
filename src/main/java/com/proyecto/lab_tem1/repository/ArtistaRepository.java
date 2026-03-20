package com.proyecto.lab_tem1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.lab_tem1.entity.Artista;
@Repository
public interface ArtistaRepository extends JpaRepository <Artista, Long>{
}
