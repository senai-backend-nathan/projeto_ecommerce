package br.com.senai.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.e_commerce.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
