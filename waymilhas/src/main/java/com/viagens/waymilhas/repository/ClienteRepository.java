package com.viagens.waymilhas.repository;


    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

import com.viagens.waymilhas.domain.entities.Cliente;
    @Repository
    public interface ClienteRepository extends JpaRepository<Cliente,Long>{
        
    }