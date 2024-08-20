package com.locadora.locadoraLivro.Renters.controllers;

import com.locadora.locadoraLivro.Renters.DTOs.CreateRenterRequestDTO;
import com.locadora.locadoraLivro.Renters.DTOs.RenterResponseDTO;
import com.locadora.locadoraLivro.Renters.mappers.RenterMapper;
import com.locadora.locadoraLivro.Renters.services.RenterServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RenterController {

    @Autowired
    RenterMapper renterMapper;

    @Autowired
    RenterServices renterServices;

    @PostMapping("/renter")
    public ResponseEntity<Void> create(@RequestBody @Valid CreateRenterRequestDTO data) {
        return renterServices.create(data);
    }

    @GetMapping("/renter")
    public ResponseEntity<List<RenterResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(renterMapper.toRenterResponseList(renterServices.findAll()));
    }

    @GetMapping("/renter/{id}")
    public ResponseEntity<RenterResponseDTO> getById(@PathVariable(value = "id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(renterMapper.toRenterResponse(renterServices.findById(id).orElseThrow(() -> new RuntimeException("Renter not found"))));
    }

    @PutMapping("/renter/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestBody @Valid CreateRenterRequestDTO createRenterRequestDTO) {
        return renterServices.update(id, createRenterRequestDTO);
    }

    @DeleteMapping("/renter/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id) {
        return renterServices.delete(id);
    }
}
