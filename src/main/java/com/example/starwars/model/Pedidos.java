package com.example.starwars.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "tb_pedidos")
public class Pedidos {
    @Id
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Products products;

    private Integer quantidade;

    
    public Integer getQuantidade() {
        return quantidade;
    }

    public Client getClient() {
        return client;
    }

    public Products getProducts() {
        return products;
    }

    public Long getId() {
        return id;
    }
}