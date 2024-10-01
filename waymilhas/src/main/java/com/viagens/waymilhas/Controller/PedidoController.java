    package com.viagens.waymilhas.Controller;

    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
    import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.PutMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import com.viagens.waymilhas.Pedido.Pedido;
import com.viagens.waymilhas.Pedido.PedidoRepository;
import com.viagens.waymilhas.Pedido.PedidoRequestDTO;
    import com.viagens.waymilhas.Pedido.PedidoResponseDTO;
import com.viagens.waymilhas.Pedido.StatusPedido;
import com.viagens.waymilhas.Produto.ProdutoResponseDTO;

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
        public PedidoResponseDTO put(@PathVariable Long id, @RequestBody @Valid PedidoResponseDTO data) {
            Pedido pedido = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
            pedido.setDataPedido(data.getDataPedido());
            pedido.setStatus(data.getStatus());
            pedido.setTotal(data.getTotal());
            pedido.setItens(pedido.getItens());
           
        
            Pedido pedidoAtualizado = repository.save(pedido); 
        
            return new PedidoResponseDTO(pedidoAtualizado); 
        }
        

        @GetMapping("/{id}")
        public PedidoResponseDTO buscar(@PathVariable Long id) {
            Pedido pedido = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

            return new PedidoResponseDTO(pedido);
        }

        @PatchMapping("/{id}/cancelar")
        public PedidoResponseDTO cancelarPedido(@PathVariable Long id) {
            Pedido pedido = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

            pedido.setStatus(StatusPedido.CANCELADO);

            repository.save(pedido);

            return new PedidoResponseDTO(pedido);
        }

        // Novo método para buscar todos os pedidos
        @GetMapping
        public List<PedidoResponseDTO> getAll() {
            List<Pedido> pedidos = repository.findAll().stream().map(PedidoResponseDTO::new).toList();
            return pedidos;
             
    }
    }