package com.example.starwars.controllers;

import com.example.starwars.model.Products;
import com.example.starwars.repository.ProductsRepository;
import com.example.starwars.request.ProductsRequest;
import com.example.starwars.services.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsRepository productsRepository;
    private final ProductsService productsService;

    public ProductsController(ProductsRepository productsRepository, ProductsService productsService) {
        this.productsRepository = productsRepository;
        this.productsService = productsService;
    }
    @GetMapping
    public List<Products> listAll() {return productsRepository.findAll();}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Products add(@RequestBody Products products) {
        return productsService.add(products);
    }

    @GetMapping("/{productsId}")
    public ResponseEntity<ProductsRequest> find(@PathVariable("productsId") Long id) {
        return productsService.findById(id);
    }
}