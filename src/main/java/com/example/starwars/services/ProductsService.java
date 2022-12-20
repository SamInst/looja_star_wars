package com.example.starwars.services;

import com.example.starwars.exceptions.EntityNotFound;
import com.example.starwars.model.Client;
import com.example.starwars.model.Products;
import com.example.starwars.repository.ProductsRepository;
import com.example.starwars.request.ProductsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Products add(Products products) {return productsRepository.save(products);}

    public ResponseEntity<ProductsRequest> findById(Long id) {
        final var products = productsRepository.findById(id).orElseThrow(() -> new EntityNotFound("Product not found"));
        String multiplies = null;
    if (products.getMult() > 1){
    multiplies = "esse produto só pode ser vendido em unidades de " + products.getMult();
    } else {
    multiplies = String.valueOf(products.getMult());
    }
        final var response = new ProductsRequest(
                products.getName(),
                products.getPriceUnit(),
                multiplies
        );
        return ResponseEntity.ok(response);
    }

}