package com.viagens.waymilhas.presentation.models.response;

import java.time.LocalDateTime;
import java.util.List;

import com.viagens.waymilhas.domain.entities.Cliente;
import com.viagens.waymilhas.domain.entities.FormaPagamento;
import com.viagens.waymilhas.domain.entities.Pedido;
import com.viagens.waymilhas.domain.entities.StatusPedido;

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
    private FormaPagamento formaPagamento;
    private List<ItemPedidoResponseDTO> itens;
    private Double total;
    private Cliente cliente;

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.itens = pedido.getItens().stream().map(ItemPedidoResponseDTO::new).toList();
        this.total = pedido.getTotal();
        this.formaPagamento = pedido.getFormaPagamento();
        this.cliente = pedido.getCliente();
    }
}
