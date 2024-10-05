package com.viagens.waymilhas.FormaPagamento;

import com.viagens.waymilhas.Controller.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento extends BaseEntity {

    String nome;
    String imagem;

    public FormaPagamento(FormaPagamentoRequestDTO data) {

        this.nome = data.getNome();
        this.imagem = data.getImagem();
    }
}
