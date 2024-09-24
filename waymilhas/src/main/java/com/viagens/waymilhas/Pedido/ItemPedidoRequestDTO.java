package com.viagens.waymilhas.Pedido;

import com.viagens.waymilhas.Produto.Produto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemPedidoRequestDTO {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "É necessario o ID do pedido")
    private Pedido pedido;

    @NotEmpty(message = "É necessario o ID do produto")
    private Produto produto;
  

    @NotEmpty(message = "É necessario o preço unitário")
    private Double precoUnitario;
    @NotEmpty(message = "É necessario a quantidade")
    private Integer quantidade;

        // Construtor que inicializa a partir de um ItemPedido existente
        public ItemPedidoRequestDTO(ItemPedido itemPedido) {
            this.id = itemPedido.getId();
            this.pedido = itemPedido.getPedido();
            this.produto = itemPedido.getProduto();
            this.quantidade = itemPedido.getQuantidade();
            this.precoUnitario = itemPedido.getPrecoUnitario();
        }
    
        // Método para calcular o total deste item (quantidade * preço unitário)
        public Double calcularTotal() {
            return this.quantidade * this.precoUnitario;
        }
    }

