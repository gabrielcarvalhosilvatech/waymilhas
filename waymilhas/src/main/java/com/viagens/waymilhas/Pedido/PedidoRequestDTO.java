package com.viagens.waymilhas.Pedido;

import java.util.List;

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

    @NotNull(message = "O cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "A forma de pagamento é obrigatório")
    private long formaPagamentoId;

    @NotNull(message = "O status do pedido é obrigatório")
    private StatusPedido status;

    @NotNull(message = "A lista de itens é obrigatória")

    private List<ItemPedidoRequestDTO> itens;

    @java.lang.SuppressWarnings(value = "all")
    @lombok.Generated

    public Double calcularTotal() {
        return itens.stream()
                .mapToDouble(item -> item.getPrecoUnitario() * item.getQuantidade())
                .sum();
    }

}
