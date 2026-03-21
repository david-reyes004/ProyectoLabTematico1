package com.proyecto.lab_tem1.controller;

import com.proyecto.lab_tem1.dto.UserDTO;
import com.proyecto.lab_tem1.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
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
}