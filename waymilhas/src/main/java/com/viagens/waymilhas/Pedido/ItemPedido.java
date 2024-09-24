/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viagens.waymilhas.Pedido;

import com.viagens.waymilhas.Produto.Produto;

import jakarta.persistence.Entity;
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

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    private Double precoUnitario;

    public ItemPedido(ItemPedidoRequestDTO data) {
        this.id = data.getId();  // Caso o ID seja passado, senão pode ser removido
        this.pedido = data.getPedido();  // Obtém o pedido do DTO
        this.produto = data.getProduto();  // Obtém o produto do DTO
        this.quantidade = data.getQuantidade();  // Obtém a quantidade do DTO
        this.precoUnitario = data.getPrecoUnitario();  // Obtém o preço unitário do DTO
    }
    
}
