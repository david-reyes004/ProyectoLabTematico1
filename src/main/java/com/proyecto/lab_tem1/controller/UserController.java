package com.proyecto.lab_tem1.controller;

import com.proyecto.lab_tem1.entity.Usuario;
import com.proyecto.lab_tem1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/guardarUsuario")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {

        userService.guardarUsuario(usuario);

        return "redirect:/PaginaPrincipal.html?nombre=" + usuario.getNombre();
    }

    @PostMapping("/loginUsuario")
    public String loginUsuario(@RequestParam String correo, @RequestParam String password) {

        Usuario usuarioEncontrado = userService.validarLogin(correo, password);

        if (usuarioEncontrado != null) {

            return "redirect:/PaginaPrincipal.html?nombre=" + usuarioEncontrado.getNombre();
        } else {

            return "redirect:/IniciarSesion.html?error";
        }
    }
}