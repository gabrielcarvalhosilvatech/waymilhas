package com.viagens.waymilhas.Pedido;

import com.viagens.waymilhas.Produto.ProdutoResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoResponseDTO {
    
        private Long id;
    
        private ProdutoResponseDTO produto;
    
        private Integer quantidade;
    
        private Double precoUnitario;
    


    public ItemPedidoResponseDTO(ItemPedido itemPedido ) {
        this.id = itemPedido.getId(); 
        this.produto = new ProdutoResponseDTO(itemPedido.getProduto());  
        this.quantidade = itemPedido.getQuantidade(); 
        this.precoUnitario = itemPedido.getPrecoUnitario(); 
    }}

