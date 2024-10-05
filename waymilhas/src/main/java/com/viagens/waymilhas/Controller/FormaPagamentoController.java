package com.viagens.waymilhas.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.viagens.waymilhas.FormaPagamento.FormaPagamento;
import com.viagens.waymilhas.FormaPagamento.FormaPagamentoRequestDTO;
import com.viagens.waymilhas.FormaPagamento.FormaPagamentoResponseDTO;

import jakarta.validation.Valid;

public class FormaPagamentoController {
      @PostMapping
    public FormaPagamentoResponseDTO saveFormaPagamento(@RequestBody @Valid FormaPagamentoRequestDTO data) {
        FormaPagamento FormaPagamentoData = new FormaPagamento(data);
        repository.save(FormaPagamentoData);
        return new FormaPagamentoResponseDTO(FormaPagamentoData);
    }
}
