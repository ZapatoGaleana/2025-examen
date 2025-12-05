package com.utez.examen.service;

import com.utez.examen.dto.ClienteDto;
import com.utez.examen.model.Cliente;
import com.utez.examen.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteService
{
    ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository)
    {
        this.clienteRepository = clienteRepository;
    }

    public Map<String, Object> crearUsuario(ClienteDto dto)
    {
        Map<String, Object> respuesta = new HashMap<>();

        if (clienteRepository.existsByEmail(dto.getEmail()))
        {
            respuesta.put("mensaje", "El email ya existe");
            respuesta.put("code",400);
            return respuesta;
        }

        Cliente cliente = new Cliente();

        cliente.setNombre(dto.getNombre());
        cliente.setTelefono(dto.getTelefono());
        cliente.setEmail(dto.getEmail());

        clienteRepository.save(cliente);
        respuesta.put("cliente", cliente);
        respuesta.put("code",200);
        return respuesta;
    }

    public Map<String, Object> getAll()
    {
        Map<String, Object> respuesta = new HashMap<>();
        List listaClientes = clienteRepository.findAll();
        respuesta.put("clientes", listaClientes);
        respuesta.put("code",200);
        return respuesta;
    }

    public Map<String, Object> obtenerClientePorId(Integer id)
    {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Cliente> optCliente = clienteRepository.findById(id);
        if (optCliente.isPresent())
        {
            respuesta.put("cliente", optCliente.get());
            respuesta.put("code",200);
        }
        else
        {
            respuesta.put("mensaje","El cliente no existe");
            respuesta.put("code",400);
        }
        return respuesta;
    }

    public Map<String, Object> actualizarUsuario(Integer id,ClienteDto dto)
    {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Cliente> optCliente = clienteRepository.findById(id);
        if (optCliente.isPresent())
        {
            if (clienteRepository.existsByEmail(optCliente.get().getEmail()))
            {
                respuesta.put("mensaje", "El email ya existe");
                respuesta.put("code",400);
            }
            Cliente cliente = optCliente.get();
            cliente.setNombre(dto.getNombre());
            cliente.setTelefono(dto.getTelefono());
            cliente.setEmail(dto.getEmail());
            clienteRepository.save(cliente);
            respuesta.put("cliente", cliente);
            respuesta.put("code",200);
        }
        return respuesta;
    }
}
