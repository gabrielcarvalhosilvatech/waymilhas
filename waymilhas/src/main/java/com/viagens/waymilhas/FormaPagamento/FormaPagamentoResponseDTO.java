package com.viagens.waymilhas.FormaPagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoResponseDTO {
    String nome;
    String imagem;

    public FormaPagamentoResponseDTO(FormaPagamento data){

this.nome = data.getNome();
this.imagem = data.getImagem();
    }
}
