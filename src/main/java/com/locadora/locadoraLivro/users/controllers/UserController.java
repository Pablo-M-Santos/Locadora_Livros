package com.locadora.locadoraLivro.users.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.locadora.locadoraLivro.users.DTOs.CreateUserRequestDTO;
import com.locadora.locadoraLivro.users.DTOs.UserResponseDTO;
import com.locadora.locadoraLivro.users.mappers.UserMapper;
import com.locadora.locadoraLivro.users.repositories.UserRepository;
import com.locadora.locadoraLivro.users.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserServices userServices;

    @PostMapping("/user")
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUserRequestDTO data) {
        return userServices.create(data);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toUserResponseList(userServices.findAll()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable(value = "id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toUserResponse(userServices.findById(id).get()));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> update(@PathVariable(value="id") int id, @RequestBody @Valid CreateUserRequestDTO createUserRequestDTO){
        return userServices.update(id, createUserRequestDTO);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value="id") int id){
        return userServices.delete(id);
    }
}