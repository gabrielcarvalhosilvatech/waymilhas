package com.viagens.waymilhas.presentation.models.response;

import com.viagens.waymilhas.domain.entities.FormaPagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoResponseDTO {

    Long id;
    String nome;
    String imagem;

    public FormaPagamentoResponseDTO(FormaPagamento data) {
        this.id = data.getId();
        this.nome = data.getNome();
        this.imagem = data.getImagem();
    }
}
