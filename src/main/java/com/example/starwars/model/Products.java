package com.example.starwars.model;

import jakarta.persistence.*;

@Entity
@Table (name = "tb_produtos")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private Float priceUnit;
    @Column
    private Float mult;

    public String getName() {
        return name;
    }

    public Float getPriceUnit() {
        return priceUnit;
    }

    public Float getMult() {
        return mult;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
