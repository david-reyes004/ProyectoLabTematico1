package com.proyecto.lab_tem1.repository;

import com.proyecto.lab_tem1.entity.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentacionRepository extends JpaRepository<Presentacion, Long> {
}