package com.viagens.waymilhas.Pedido;

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

    @NotEmpty(message = "É necessario o ID do produto")
    private Long idProduto;

    @NotEmpty(message = "É necessario o preço unitário")
    private Double precoUnitario;
    @NotEmpty(message = "É necessario a quantidade")
    private Integer quantidade;

}
