package com.example.cursomc.services;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente buscar (Integer id) {
        Optional <Cliente> obj = repo.findById(id);
        return obj.orElse(null);
    }
}
