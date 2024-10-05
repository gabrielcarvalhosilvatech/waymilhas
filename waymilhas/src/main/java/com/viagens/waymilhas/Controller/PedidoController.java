package com.viagens.waymilhas.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viagens.waymilhas.Pedido.ItemPedido;
import com.viagens.waymilhas.Pedido.Pedido;
import com.viagens.waymilhas.Pedido.PedidoRepository;
import com.viagens.waymilhas.Pedido.PedidoRequestDTO;
import com.viagens.waymilhas.Pedido.PedidoResponseDTO;
import com.viagens.waymilhas.Pedido.StatusPedido;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @Transactional
    public Pedido savePedido(Pedido pedido) {
        Pedido mergedPedido = repository.save(pedido);
        return mergedPedido;
    }

    @PostMapping
    public PedidoResponseDTO criarPedido(@RequestBody @Valid PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = new Pedido(pedidoRequestDTO);

        pedido.setTotal(pedido.somarTotal());
        
        repository.save(pedido);

        pedido = repository.findById(pedido.getId()).get();

        return new PedidoResponseDTO(pedido);
    }

    @PutMapping("/{id}")
    public PedidoResponseDTO put(@PathVariable Long id, @RequestBody @Valid PedidoRequestDTO data) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        try {
            pedido.setStatus(data.getStatus());

            List<ItemPedido> itens = new ArrayList<>();

            data.getItens().forEach(item -> {
                if (item.getId() == null) {
                    var newItem = new ItemPedido(item);
                    newItem.setPedido(pedido);
                    itens.add(newItem);
                } else {
                    // Encontra o item existente pelo ID
                    var existingItem = pedido.getItens().stream()
                            .filter(itemTmp -> itemTmp.getId().equals(item.getId()))
                            .findFirst()
                            .orElse(null);

                    if (existingItem != null) {
                        existingItem.setPrecoUnitario(item.getPrecoUnitario());
                        existingItem.setQuantidade(item.getQuantidade());
                        itens.add(existingItem);
                    }
                }
            });

            pedido.setItens(itens);
            pedido.setTotal(pedido.somarTotal());

            Pedido pedidoAtualizado = repository.save(pedido);
            return new PedidoResponseDTO(pedidoAtualizado);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO buscar(@PathVariable Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        return new PedidoResponseDTO(pedido);
    }

    @PatchMapping("/{id}")
    public PedidoResponseDTO cancelarPedido(@PathVariable Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(StatusPedido.CANCELADO);

        repository.save(pedido);

        return new PedidoResponseDTO(pedido);
    }

    @GetMapping
    public List<PedidoResponseDTO> getAll() {
        try {
            List<PedidoResponseDTO> pedidos = repository.findAll().stream().map(PedidoResponseDTO::new).toList();

            return pedidos;
        } catch (Exception e) {
            throw e;
        }

    }

    @DeleteMapping("/{id}")
    public void deletarPedido(@PathVariable Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        repository.delete(pedido);
    }

}
