package com.proyecto.lab_tem1.controller;

import com.proyecto.lab_tem1.dto.UserDTO;
import com.proyecto.lab_tem1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.lab_tem1.dto.LoginDTO;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping("/usuarios")
    public List<UserDTO> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/usuarios")
    public UserDTO save(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @GetMapping("/usuarios/{id}")
    public UserDTO getUser(@PathVariable Long id){
        return userService.findByid(id);
    }


    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try {
            UserDTO usuario = userService.inicioSesion(loginDTO);
            return ResponseEntity.ok(usuario);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
