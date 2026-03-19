package com.proyecto.lab_tem1.service;

import com.proyecto.lab_tem1.entity.Usuario;
import com.proyecto.lab_tem1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void guardarUsuario(Usuario usuario) {
        userRepository.save(usuario);
    }

    public Usuario validarLogin(String correo, String password) {
        return userRepository.findByCorreoAndPassword(correo, password);
    }
}