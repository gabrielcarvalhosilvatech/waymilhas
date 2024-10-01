package com.viagens.waymilhas.Pedido;

import java.time.LocalDateTime;
import java.util.List;

import com.viagens.waymilhas.Cliente.ClienteResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDTO {

    private Long id;
    private LocalDateTime dataPedido;
    private StatusPedido status;
    private List<ItemPedidoResponseDTO> itens;
    private Double total;
    private ClienteResponseDTO cliente; 

    

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();  
        this.dataPedido = pedido.getDataPedido(); 
        this.status = pedido.getStatus(); 
        this.itens = pedido.getItens().stream().map(ItemPedidoResponseDTO::new).toList();
        this.total = pedido.getTotal();  
    }
}
