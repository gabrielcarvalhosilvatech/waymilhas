package com.viagens.waymilhas.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.viagens.waymilhas.domain.entities.Produto;
import com.viagens.waymilhas.domain.services.ProdutoService;
import com.viagens.waymilhas.presentation.models.request.ProdutoRequestDTO;
import com.viagens.waymilhas.presentation.models.response.ProdutoResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ProdutoResponseDTO saveProduto(@RequestBody @Valid ProdutoRequestDTO data) {
        Produto produtoData = new Produto(data);
        Produto produtoSalvo = produtoService.saveProduto(produtoData);
        return new ProdutoResponseDTO(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO put(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO data) {
        Produto produtoAtualizado = new Produto(data);
        Produto produto = produtoService.atualizarProduto(id, produtoAtualizado);
        return new ProdutoResponseDTO(produto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscar(@PathVariable Long id) {
        Produto produto = produtoService.buscarProduto(id);
        return new ProdutoResponseDTO(produto);
    }

    @GetMapping
    public List<ProdutoResponseDTO> getAll() {
        List<Produto> produtos = produtoService.getAll();
        return produtos.stream().map(ProdutoResponseDTO::new).toList();
    }
}
