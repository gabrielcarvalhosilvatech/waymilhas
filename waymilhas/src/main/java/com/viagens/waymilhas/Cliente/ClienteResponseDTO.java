package com.viagens.waymilhas.Cliente;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO{

    private Long id;
    
 
    private String nome;

   
    private String email;

   
    private String cpf;

    
    private String nascimento;

    public ClienteResponseDTO(Cliente cliente){

        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
        this.nascimento = cliente.getNascimento();
    }
}
