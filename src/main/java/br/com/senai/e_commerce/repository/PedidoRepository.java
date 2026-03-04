package br.com.senai.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.e_commerce.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
