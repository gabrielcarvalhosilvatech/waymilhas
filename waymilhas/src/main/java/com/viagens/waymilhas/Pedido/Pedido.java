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
import jakarta.validation.Valid;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private final  Cliente clienteId;

    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;

    private Double total;

    // Getters e Setters
    
        public Pedido(@Valid PedidoRequestDTO data) {
            this.id = data.getId();  // Caso o ID seja passado, senão pode ser removido
            this.clienteId =data.getClienteId();  // Certifique-se de que PedidoRequestDTO tenha o cliente
            this.dataPedido = data.getDataPedido();  // Data do pedido no DTO
            this.status = data.getStatus();  // Status do pedido
            this.itens = data.getItens();  // Lista de itens do pedido no DTO
     
        }
        
    

    public Double somarTotal() {
        return itens.stream()
                .mapToDouble(item -> item.getPrecoUnitario() * item.getQuantidade())  // Multiplica preço pela quantidade
                .sum();
    }


    
}