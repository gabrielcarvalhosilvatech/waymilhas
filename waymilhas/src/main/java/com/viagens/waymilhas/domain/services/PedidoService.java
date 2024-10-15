package com.viagens.waymilhas.domain.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viagens.waymilhas.domain.entities.ItemPedido;
import com.viagens.waymilhas.domain.entities.Pedido;
import com.viagens.waymilhas.domain.entities.StatusPedido;
import com.viagens.waymilhas.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Transactional
    public Pedido criarPedido(Pedido pedido) {
        pedido.setTotal(pedido.somarTotal());
        return repository.save(pedido);
    }

    @Transactional
    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(pedidoAtualizado.getStatus());

        List<ItemPedido> itens = new ArrayList<>();
        pedidoAtualizado.getItens().forEach(item -> {
            if (item.getId() == null) {
                item.setPedido(pedido);
                itens.add(item);
            } else {
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
        return repository.save(pedido);
    }

    public Pedido findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Pedido orderCancel(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(StatusPedido.CANCELADO);
        return repository.save(pedido);
    }

    public List<Pedido> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        repository.delete(pedido);
    }
}
