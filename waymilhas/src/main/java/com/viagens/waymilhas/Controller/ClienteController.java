package com.viagens.waymilhas.Controller;

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

import com.viagens.waymilhas.Cliente.Cliente;
import com.viagens.waymilhas.Cliente.ClienteRepository;
import com.viagens.waymilhas.Cliente.ClienteRequestDTO;
import com.viagens.waymilhas.Cliente.ClienteResponseDTO;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    // Endpoint para salvar um cliente
    @PostMapping
    public ClienteResponseDTO saveCliente(@RequestBody @Valid ClienteRequestDTO data) {
        Cliente clienteData = new Cliente(data);
        repository.save(clienteData);
        return new ClienteResponseDTO(clienteData);
    }

    // Endpoint para atualizar um cliente pelo ID
    @PutMapping("/{id}")
    public ClienteResponseDTO put(@PathVariable Long id, @RequestBody @Valid ClienteRequestDTO data) {
        Cliente cliente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(data.getNome());
        cliente.setEmail(data.getEmail());
        cliente.setNascimento(data.getNascimento());
        cliente.setCpf(data.getCpf());
        repository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }

    // Endpoint para deletar um cliente pelo ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Endpoint para buscar um cliente pelo ID
    @GetMapping("/{id}")
    public ClienteResponseDTO buscar(@PathVariable Long id) {
        Cliente cliente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return new ClienteResponseDTO(cliente);
    }

    // Endpoint para buscar todos os clientes
    @GetMapping
    public List<ClienteResponseDTO> getALL() {
        List<ClienteResponseDTO> clienteList = repository.findAll().stream()
            .map(ClienteResponseDTO::new)
            .toList();
        return clienteList;
    }
}
