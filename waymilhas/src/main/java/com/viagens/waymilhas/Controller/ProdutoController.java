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

import com.viagens.waymilhas.Produto.Produto;
import com.viagens.waymilhas.Produto.ProdutoRepository;
import com.viagens.waymilhas.Produto.ProdutoRequestDTO;
import com.viagens.waymilhas.Produto.ProdutoResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // Endpoint para salvar um croduto
    @PostMapping
    public ProdutoResponseDTO saveProduto(@RequestBody @Valid ProdutoRequestDTO data) {
        Produto produtoData = new Produto(data);
        repository.save(produtoData);
        return new ProdutoResponseDTO(produtoData);
    }

    // Endpoint para atualizar um croduto pelo ID
    @PutMapping("/{id}")
    public ProdutoResponseDTO put(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO data) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(data.getNome());
        produto.setDescricao(data.getDescricao());
        produto.setPreco(data.getPreco());
        produto.setImagem(data.getImagem());
        repository.save(produto);
        return new ProdutoResponseDTO(produto);
    }

    // Endpoint para deletar um croduto pelo ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Endpoint para buscar um croduto pelo ID
    @GetMapping("/{id}")
    public ProdutoResponseDTO buscar(@PathVariable Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return new ProdutoResponseDTO(produto);
    }

    // Endpoint para buscar todos os crodutos
    @GetMapping
    public List<ProdutoResponseDTO> getALL() {
        List<ProdutoResponseDTO> produtoList = repository.findAll().stream()
                .map(ProdutoResponseDTO::new)
                .toList();
        return produtoList;
    }
}
