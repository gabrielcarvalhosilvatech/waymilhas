package com.viagens.waymilhas.Pedido;

import com.viagens.waymilhas.Produto.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;

    private Pedido pedido;

    private Produto produto;

    private Integer quantidade;

    private Double precoUnitario;

    public ItemDTO(ItemPedido data) {
        this.id = data.getId();
        this.pedido = data.getPedido();
        this.produto = data.getProduto();
        this.quantidade = data.getQuantidade();
        this.precoUnitario = data.getPrecoUnitario();
    }
}
