

package com.viagens.waymilhas.Pedido;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viagens.waymilhas.Cliente.Cliente;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long>{

    public Object findAll(Cliente clienteId);
}
    
