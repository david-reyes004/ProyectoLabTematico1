package com.proyecto.lab_tem1.repository;

import com.proyecto.lab_tem1.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCorreoAndPassword(String correo, String password);

}