    package com.viagens.waymilhas.domain.entities;

    import com.viagens.waymilhas.domain.Base.BaseEntity;
import com.viagens.waymilhas.presentation.models.request.FormaPagamentoRequestDTO;

import jakarta.persistence.Entity;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    @Entity   
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class FormaPagamento extends BaseEntity {

        String nome;
        String imagem;
public FormaPagamento(Long id){
    this.id = id;

}
        public FormaPagamento(FormaPagamentoRequestDTO data) {

            this.nome = data.getNome();
            this.imagem = data.getImagem();
        }
    }
