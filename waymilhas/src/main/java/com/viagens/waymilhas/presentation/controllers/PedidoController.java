package com.viagens.waymilhas.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.viagens.waymilhas.domain.entities.Pedido;
import com.viagens.waymilhas.domain.services.PedidoService;
import com.viagens.waymilhas.presentation.models.request.PedidoRequestDTO;
import com.viagens.waymilhas.presentation.models.response.PedidoResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public PedidoResponseDTO isert(@RequestBody @Valid PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = new Pedido(pedidoRequestDTO);
        Pedido pedidoCriado = pedidoService.criarPedido(pedido);
        return new PedidoResponseDTO(pedidoCriado);
    }

    @PutMapping("/{id}")
    public PedidoResponseDTO put(@PathVariable Long id, @RequestBody @Valid PedidoRequestDTO data) {
        Pedido pedidoAtualizado = new Pedido(data);
        Pedido pedido = pedidoService.atualizarPedido(id, pedidoAtualizado);
        return new PedidoResponseDTO(pedido);
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO buscar(@PathVariable Long id) {
        Pedido pedido = pedidoService.findById(id);
        return new PedidoResponseDTO(pedido);
    }

    @PatchMapping("/{id}")
    public PedidoResponseDTO cancelar(@PathVariable Long id) {
        Pedido pedido = pedidoService.orderCancel(id);
        return new PedidoResponseDTO(pedido);
    }

    @GetMapping
    public List<PedidoResponseDTO> getAll() {
        List<Pedido> pedidos = pedidoService.getAll();
        return pedidos.stream().map(PedidoResponseDTO::new).toList();
    }

    @DeleteMapping("/{id}")
    public void deletarPedido(@PathVariable Long id) {
        pedidoService.delete(id);
    }
}
