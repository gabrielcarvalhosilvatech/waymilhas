package com.viagens.waymilhas.FormaPagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoRequestDTO {

    @NotBlank
    String nome;

    @NotNull
    String imagem;
}
