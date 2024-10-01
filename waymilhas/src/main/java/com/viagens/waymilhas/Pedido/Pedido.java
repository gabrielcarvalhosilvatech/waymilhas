package com.viagens.waymilhas.Pedido;

import java.time.LocalDateTime;
import java.util.List;

import com.viagens.waymilhas.Cliente.Cliente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Table(name = "pedido")
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDateTime dataPedido;
   
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
   
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemPedido> itens;
    private Double total;
  
    public Pedido(PedidoRequestDTO data) { 
        this.cliente = new Cliente(data.getClienteId());
        this.dataPedido = LocalDateTime.now();
        this.status = data.getStatus(); 
        this.itens = data.getItens().stream().map(itemDTO -> {
            ItemPedido item = new ItemPedido(itemDTO);
            item.setPedido(this);
            return item;
        }).toList();  
    }

    public Double somarTotal() {
        return itens.stream()
                .mapToDouble(item -> item.getPrecoUnitario() * item.getQuantidade()) // Multiplica preço pela quantidade
                .sum();
    }
}
