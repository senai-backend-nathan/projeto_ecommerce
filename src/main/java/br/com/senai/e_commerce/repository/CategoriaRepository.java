
package br.com.senai.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.e_commerce.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
