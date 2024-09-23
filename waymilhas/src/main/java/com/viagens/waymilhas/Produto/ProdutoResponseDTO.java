package com.viagens.waymilhas.Produto;


    




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO{

    private Long id;
    

    private String nome;

 
    private String descricao;

   
    private Float preco;

  
    private String imagem;

    public ProdutoResponseDTO(Produto produto){

        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.imagem = produto.getImagem();
   
    }
}


