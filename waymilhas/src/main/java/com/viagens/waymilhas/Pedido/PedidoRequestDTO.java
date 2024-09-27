package com.viagens.waymilhas.Pedido;

import java.time.LocalDateTime;
import java.util.List;

import com.viagens.waymilhas.Cliente.Cliente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDTO {

    private Long id;

    @NotNull(message = "O cliente é obrigatório")
    @Valid
    private  Cliente clienteId=null;

    @NotNull(message = "A data do pedido é obrigatória")
    private LocalDateTime dataPedido;

    @NotNull(message = "O status do pedido é obrigatório")
    private StatusPedido status;

    @NotNull(message = "A lista de itens é obrigatória")
    @Valid
    private List<ItemPedido> itens;

    @java.lang.SuppressWarnings(value = "all")
    @lombok.Generated
   
    



    public Double calcularTotal() {
        return itens.stream()
                .mapToDouble(item -> item.getPrecoUnitario() * item.getQuantidade())
                .sum();
    }

    // Construtor que inicializa o PedidoRequestDTO a partir de um Pedido existente
    public PedidoRequestDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.clienteId = pedido.getClienteId();
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.itens = pedido.getItens();
    }

}
