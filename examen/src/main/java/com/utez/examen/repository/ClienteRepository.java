package com.utez.examen.repository;

import com.utez.examen.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>
{
    boolean existsByEmail(String email);
}
