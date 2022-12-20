package com.example.starwars.request;

public record ProductsRequest(
        String name,
        Float price_unit,
        String multiplier
) {
}