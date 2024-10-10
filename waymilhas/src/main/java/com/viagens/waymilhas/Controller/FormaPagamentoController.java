package com.viagens.waymilhas.Controller;

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

import com.viagens.waymilhas.FormaPagamento.FormaPagamento;
import com.viagens.waymilhas.FormaPagamento.FormaPagamentoRepository;
import com.viagens.waymilhas.FormaPagamento.FormaPagamentoRequestDTO;
import com.viagens.waymilhas.FormaPagamento.FormaPagamentoResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/forma-pagamento") // Definindo a rota base para a forma de pagamento
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository repository;  // Injetando o repository

    @PostMapping
    public FormaPagamentoResponseDTO saveFormaPagamento(@RequestBody @Valid FormaPagamentoRequestDTO data) {
        FormaPagamento formaPagamentoData = new FormaPagamento(data);
        repository.save(formaPagamentoData);  // Salvando a entidade no banco
        return new FormaPagamentoResponseDTO(formaPagamentoData);
    }

    @PutMapping
    public FormaPagamentoResponseDTO put(@PathVariable long id, @RequestBody @Valid FormaPagamentoRequestDTO data) {
        FormaPagamento formaPagamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        formaPagamento.setImagem(data.getImagem());
        formaPagamento.setNome(data.getNome());
        
        repository.save(formaPagamento);
        return new FormaPagamentoResponseDTO(formaPagamento);
    }
     
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
       @GetMapping("/{id}")
    public FormaPagamentoResponseDTO buscar(@PathVariable Long id) {
        FormaPagamento formaPagamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("FormaPagamento não encontrado"));

        return new FormaPagamentoResponseDTO(formaPagamento);
    }
    @GetMapping
    public List<FormaPagamentoResponseDTO> getALL() {
        List<FormaPagamentoResponseDTO> FormaPagamentoList = repository.findAll().stream()
                .map(FormaPagamentoResponseDTO::new)
                .toList();
        return FormaPagamentoList;
    }
}
