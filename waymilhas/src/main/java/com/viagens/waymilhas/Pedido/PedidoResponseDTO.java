package com.viagens.waymilhas.Pedido;

import java.time.LocalDateTime;
import java.util.List;

import com.viagens.waymilhas.Cliente.ClienteResponseDTO; // Certifique-se de que você tenha esse DTO

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
    private List<ItemPedido> itens;
    private Double total;
    private ClienteResponseDTO cliente;  // Incluindo o cliente na resposta

    // Construtor que aceita um Pedido
    public PedidoResponseDTO(Pedido pedido) {
        this.id = getId();  // ID do pedido
        this.dataPedido = getDataPedido();  // Data do pedido
        this.status = getStatus();  // Status do pedido
        this.itens = getItens();  // Itens do pedido
        this.total = getTotal();  // Valor total
    }
}
