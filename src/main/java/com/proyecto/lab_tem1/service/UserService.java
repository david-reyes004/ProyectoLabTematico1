package com.proyecto.lab_tem1.service;

import com.proyecto.lab_tem1.dto.LoginDTO;
import com.proyecto.lab_tem1.dto.UserDTO;
import com.proyecto.lab_tem1.entity.Usuario;
import org.springframework.stereotype.Service;
import com.proyecto.lab_tem1.repository.UserRepository;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll()
    {
        List<UserDTO> lista = new ArrayList<>();
        List<Usuario> usuarios = userRepository.findAll();

        for (Usuario usuario : usuarios) {
            UserDTO userDTO = new UserDTO();

            userDTO.setId(usuario.getId());
            userDTO.setNombre(usuario.getNombre());
            userDTO.setCorreo(usuario.getCorreo());
            userDTO.setPassword(usuario.getPassword());
            lista.add(userDTO);
        }
        return lista;
    }


    public UserDTO save(UserDTO userDTO) {
        if(userRepository.findByCorreo(userDTO.getCorreo()).isPresent()){
            throw new RuntimeException("El correo ya existe");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(userDTO.getNombre());
        usuario.setCorreo(userDTO.getCorreo());
        usuario.setPassword(userDTO.getPassword());

        Usuario usuario2 = userRepository.save(usuario);
        userDTO.setId(usuario2.getId());
        return userDTO;
    }

    public UserDTO findByid(Long id) {
        Usuario usuario = userRepository.findById(id).get();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(usuario.getId());
        userDTO.setNombre(usuario.getNombre());
        userDTO.setCorreo(usuario.getCorreo());
        return userDTO;
    }
    public UserDTO inicioSesion(LoginDTO loginDTO) {
        Optional<Usuario> usuarioOptional = userRepository.findByCorreo(loginDTO.getCorreo());

        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("El correo no está registrado");
        }

        Usuario usuario = usuarioOptional.get();

        if (!usuario.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        UserDTO resultado = new UserDTO();
        resultado.setId(usuario.getId());
        resultado.setNombre(usuario.getNombre());
        resultado.setCorreo(usuario.getCorreo());
        return resultado;
    }
}
