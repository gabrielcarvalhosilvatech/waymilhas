package com.viagens.waymilhas.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viagens.waymilhas.Cliente.Cliente;
import com.viagens.waymilhas.Pedido.ItemPedido;
import com.viagens.waymilhas.Pedido.ItemPedidoRequestDTO;
import com.viagens.waymilhas.Pedido.Pedido;
import com.viagens.waymilhas.Pedido.PedidoRepository;
import com.viagens.waymilhas.Pedido.PedidoRequestDTO;
import com.viagens.waymilhas.Pedido.PedidoResponseDTO;
import com.viagens.waymilhas.Pedido.StatusPedido;
import com.viagens.waymilhas.Produto.Produto;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;
    @Transactional
    public Pedido savePedido(Pedido pedido) {
        // Atualiza ou persiste o Pedido e seus ItemPedido
        Pedido mergedPedido = repository.save(pedido);
        return mergedPedido;
    }
    
public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody @Valid PedidoRequestDTO pedidoRequest) {
    Cliente cliente = repository.findAll(pedidoRequest.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    Pedido pedido = new Pedido(pedidoRequest);
    pedido.setCliente(cliente);  // Atribui o cliente buscado

    List<ItemPedido> itens = new ArrayList<>();
    for (ItemPedidoRequestDTO itemDTO : pedidoRequest.getItens()) {
        Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        ItemPedido item = new ItemPedido(itemDTO);
        item.setProduto(produto);  // Atribui o produto buscado ao item
        itens.add(item);
    }
    pedido.setItens(itens);

    pedido.setTotal(pedido.somarTotal());  // Calcule o total baseado nos itens
    Pedido savedPedido = repository.save(pedido);
    
    // Retorna PedidoResponseDTO preenchido com as informações do pedido e do cliente
    return ResponseEntity.status(HttpStatus.CREATED).body(new PedidoResponseDTO(savedPedido));
}

 
    // Método para atualizar um pedido
    //Localhost:8080/pedido/8
    @PutMapping("/{id}")
    public PedidoResponseDTO put(@PathVariable Long id, @RequestBody @Valid PedidoResponseDTO data) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setDataPedido(data.getDataPedido());
        pedido.setStatus(data.getStatus());
        pedido.setTotal(data.getTotal());
        pedido.setItens(data.getItens());

        repository.save(pedido);
        return new PedidoResponseDTO(pedido);
    }

    // Método para buscar um pedido por ID
    @GetMapping("/{id}")
    public PedidoResponseDTO buscar(@PathVariable Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        return new PedidoResponseDTO(pedido);
    }

    // Método para cancelar um pedido (soft delete)
    @PatchMapping("/{id}/cancelar")
    public PedidoResponseDTO cancelarPedido(@PathVariable Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Atualiza o status do pedido para CANCELADO
        pedido.setStatus(StatusPedido.CANCELADO);

        // Salva a atualização no banco de dados
        repository.save(pedido);

        // Retorna o DTO atualizado
        return new PedidoResponseDTO(pedido);
    }

    // Novo método para buscar todos os pedidos
    @GetMapping
    public List<PedidoResponseDTO> buscarTodos() {
        List<Pedido> pedidos = repository.findAll(); // Busca todos os pedidos do banco de dados
        return pedidos.stream() // Converte a lista de pedidos em uma lista de PedidoResponseDTO
                .map(PedidoResponseDTO::new)
                .toList(); // Retorna a lista de PedidoResponseDTO
    }
    public PedidoRepository getRepository() {
        return repository;
    }
    public void setRepository(PedidoRepository repository) {
        this.repository = repository;
    }
}
