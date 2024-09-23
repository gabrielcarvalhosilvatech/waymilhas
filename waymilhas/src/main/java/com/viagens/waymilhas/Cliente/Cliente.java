package com.viagens.waymilhas.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "clientes")
@Entity(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Nome;

    private String Email;

    private String Cpf;

    private String Nascimento;

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(ClienteRequestDTO data) {

        this.Nome = data.getNome();
        this.Email = data.getEmail();
        this.Cpf = data.getCpf();
        this.Nascimento = data.getNascimento();
    }
}
