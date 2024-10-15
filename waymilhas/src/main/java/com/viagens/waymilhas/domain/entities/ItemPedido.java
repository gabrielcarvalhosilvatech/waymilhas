package com.viagens.waymilhas.domain.entities;

import com.viagens.waymilhas.presentation.models.request.ItemPedidoRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    private Double precoUnitario;

    public ItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO) {
        this.produto = new Produto(itemPedidoRequestDTO.getProdutoId());
        this.quantidade = itemPedidoRequestDTO.getQuantidade();
        this.precoUnitario = itemPedidoRequestDTO.getPrecoUnitario();
    }
}
