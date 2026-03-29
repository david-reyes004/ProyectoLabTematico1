package com.proyecto.lab_tem1.repository;

import org.springframework.stereotype.Repository;
import com.proyecto.lab_tem1.entity.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface EventosRepository extends JpaRepository<Eventos, Long>{

}
