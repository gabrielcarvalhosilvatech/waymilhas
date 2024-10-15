package com.viagens.waymilhas.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viagens.waymilhas.domain.entities.FormaPagamento;
import com.viagens.waymilhas.domain.services.FormaPagamentoService;
import com.viagens.waymilhas.presentation.models.request.FormaPagamentoRequestDTO;
import com.viagens.waymilhas.presentation.models.response.FormaPagamentoResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/forma-pagamento") 
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @PostMapping
    public FormaPagamentoResponseDTO saveFormaPagamento(@RequestBody @Valid FormaPagamentoRequestDTO data) {
        FormaPagamento formaPagamentoData = new FormaPagamento(data);
        formaPagamentoService.save(formaPagamentoData);
        return new FormaPagamentoResponseDTO(formaPagamentoData);
    }

    @PutMapping("/{id}")
    public FormaPagamentoResponseDTO put(@PathVariable long id, @RequestBody @Valid FormaPagamentoRequestDTO data) {
        FormaPagamento formaPagamentoAtualizado = new FormaPagamento(data);
        FormaPagamento formaPagamento = formaPagamentoService.update(id, formaPagamentoAtualizado);
        return new FormaPagamentoResponseDTO(formaPagamento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        formaPagamentoService.delete(id);
    }

    @GetMapping("/{id}")
    public FormaPagamentoResponseDTO buscar(@PathVariable Long id) {
        FormaPagamento formaPagamento = formaPagamentoService.findById(id);
        return new FormaPagamentoResponseDTO(formaPagamento);
    }

    @GetMapping
    public List<FormaPagamentoResponseDTO> getALL() {
        List<FormaPagamentoResponseDTO> formaPagamentoList = formaPagamentoService.getAll().stream()
                .map(FormaPagamentoResponseDTO::new)
                .toList();
        return formaPagamentoList;
    }
}
