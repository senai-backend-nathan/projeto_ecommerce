package br.com.senai.e_commerce.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//aplicações da fk cliente e fk produto 


    private LocalDateTime data;
    private Integer quantidade;
    private Boolean status;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
