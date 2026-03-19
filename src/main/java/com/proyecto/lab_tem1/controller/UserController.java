package com.proyecto.lab_tem1.controller;

import com.proyecto.lab_tem1.entity.Usuario;
import com.proyecto.lab_tem1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/guardarUsuario")
    public String registrarUsuario(Usuario usuario) {

        userService.guardarUsuario(usuario);

        // Si todo sale bien, lo regresamos a la pantalla de Login
        return "redirect:/IniciarSesion.html";
    }
}