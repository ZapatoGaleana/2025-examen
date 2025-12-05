package com.utez.examen.controller;


import com.utez.examen.dto.ClienteDto;
import com.utez.examen.repository.ClienteRepository;
import com.utez.examen.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ControllerCliente
{
    ClienteService clienteService;

    public ControllerCliente(ClienteService clienteService)
    {
        this.clienteService = clienteService;
    }

     @PostMapping("")
    public ResponseEntity<Object> create (@RequestBody ClienteDto clienteDto)
    {
        Map<String, Object> respuesta = clienteService.crearUsuario(clienteDto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody ClienteDto clienteDto)
    {
        Map<String, Object> respuesta = clienteService.actualizarUsuario(id, clienteDto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id)
    {
        Map<String, Object> respuesta = clienteService.obtenerClientePorId(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Object> getAll()
    {
        Map<String, Object> respuesta = clienteService.getAll();
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
