package com.viagens.waymilhas.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viagens.waymilhas.domain.entities.Produto;
import com.viagens.waymilhas.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto saveProduto(Produto produto) {
        return repository.save(produto);
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setImagem(produtoAtualizado.getImagem());
        return repository.save(produto);
    }

    public void deletarProduto(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        repository.delete(produto);
    }

    public Produto buscarProduto(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public List<Produto> getAll() {
        return repository.findAll();
    }
}
