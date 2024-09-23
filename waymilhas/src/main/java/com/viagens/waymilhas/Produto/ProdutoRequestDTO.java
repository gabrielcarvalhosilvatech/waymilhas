
package com.viagens.waymilhas.Produto;




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
    public class ProdutoRequestDTO{
    
        private Long id;
        
        @NotBlank(message = "Nome é obrigatório")
        private String nome;
    
        
        @NotBlank(message = "Descrição é obrigatória")
        private String descricao;

        @NotNull(message = "Preço é obrigatório")
        private Float preco;

        @NotNull(message = "URL de uma imagem é obrigatório")
    private String imagem;
    
    
        public ProdutoRequestDTO(Produto produto){
    
            this.id = produto.getId();
            this.nome = produto.getNome();
            this.descricao = produto.getDescricao();
            this.imagem = produto.getImagem();
        }

    
}

