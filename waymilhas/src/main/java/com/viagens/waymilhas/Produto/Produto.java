package com.viagens.waymilhas.Produto;

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

@Table(name = "produtos")
@Entity(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private Float preco;

    private String imagem;

    public Produto(ProdutoRequestDTO data) {

        this.nome = data.getNome();
        this.descricao = data.getDescricao();
        this.preco = data.getPreco();
        this.imagem = data.getImagem();
    }

    public Produto(Long id) {

        this.id = id;
    }
}
