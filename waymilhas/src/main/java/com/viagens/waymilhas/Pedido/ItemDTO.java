package com.viagens.waymilhas.Pedido;

import com.viagens.waymilhas.Produto.Produto;

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
}}