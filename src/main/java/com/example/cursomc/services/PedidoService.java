package com.example.cursomc.services;

import com.example.cursomc.domain.Pedido;
import com.example.cursomc.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido buscar (Integer id) {
        Optional <Pedido> obj = repo.findById(id);
        return obj.orElse(null);
    }
}
