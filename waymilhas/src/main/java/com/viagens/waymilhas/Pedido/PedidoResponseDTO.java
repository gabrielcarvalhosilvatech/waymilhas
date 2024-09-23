package com.viagens.waymilhas.Pedido;

import java.time.LocalDateTime;
import java.util.List;  

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;


    private Double total;

    // Getters e Setters

    public PedidoResponseDTO(Pedido pedido) {
        this.id = getId();
        this.dataPedido = getDataPedido();
        this.status = getStatus();
        this.itens = getItens();
        this.total = getTotal();
        }
  
}