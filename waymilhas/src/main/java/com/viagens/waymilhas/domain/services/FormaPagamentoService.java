package com.viagens.waymilhas.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viagens.waymilhas.domain.entities.FormaPagamento;
import com.viagens.waymilhas.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository repository;

    public FormaPagamento save(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    public FormaPagamento update(Long id, FormaPagamento formaPagamentoAtualizado) {
        FormaPagamento formaPagamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada"));

        formaPagamento.setNome(formaPagamentoAtualizado.getNome());
        formaPagamento.setImagem(formaPagamentoAtualizado.getImagem());
        return repository.save(formaPagamento);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public FormaPagamento findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada"));
    }

    public List<FormaPagamento> getAll() {
        return repository.findAll();
    }
}
