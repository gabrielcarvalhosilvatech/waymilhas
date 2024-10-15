package com.viagens.waymilhas.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viagens.waymilhas.domain.entities.Cliente;
import com.viagens.waymilhas.domain.services.ClienteService;
import com.viagens.waymilhas.presentation.models.request.ClienteRequestDTO;
import com.viagens.waymilhas.presentation.models.response.ClienteResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ClienteResponseDTO saveCliente(@RequestBody @Valid ClienteRequestDTO data) {
        Cliente clienteData = new Cliente(data);
        clienteService.save(clienteData);
        return new ClienteResponseDTO(clienteData);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO put(@PathVariable Long id, @RequestBody @Valid ClienteRequestDTO data) {
        Cliente clienteAtualizado = new Cliente(data);
        Cliente cliente = clienteService.update(id, clienteAtualizado);
        return new ClienteResponseDTO(cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clienteService.delete(id);
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscar(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return new ClienteResponseDTO(cliente);
    }

    @GetMapping
    public List<ClienteResponseDTO> getALL() {
        List<ClienteResponseDTO> clienteList = clienteService.getAllClientes().stream()
            .map(ClienteResponseDTO::new)
            .toList();
        return clienteList;
    }
}
