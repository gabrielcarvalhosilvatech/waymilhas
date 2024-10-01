package com.viagens.waymilhas.Pedido;

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
    @NotEmpty(message = "É necessario o ID do produto")
    private  Long produtoId;
    @NotEmpty(message = "É necessario o preço unitário")
    private Double precoUnitario;
    @NotEmpty(message = "É necessario a quantidade")
    private Integer quantidade;

       
}
