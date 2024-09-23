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
    

    @NotNull
    private Long idCliente;    

    @NotNull
    private List<ItemPedidoRequestDTO> itens;

}
