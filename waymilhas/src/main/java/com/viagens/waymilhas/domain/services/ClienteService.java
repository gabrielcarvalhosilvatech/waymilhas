package com.viagens.waymilhas.domain.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viagens.waymilhas.domain.entities.Cliente;
import com.viagens.waymilhas.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente update(Long id, Cliente clienteAtualizado) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setNascimento(clienteAtualizado.getNascimento());
        cliente.setCpf(clienteAtualizado.getCpf());
        return repository.save(cliente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Cliente findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public List<Cliente> getAllClientes() {
        return repository.findAll();
    }
}
