package com.proyecto.lab_tem1.repository; /*Quedó igual*/

import com.proyecto.lab_tem1.entity.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {
}