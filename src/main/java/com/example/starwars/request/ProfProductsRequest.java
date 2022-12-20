package com.example.starwars.request;

public record ProfProductsRequest(
        String name,
        Float price_unit,
        Float multiplier,
        Integer amount,
        Float total,
        String profitability
) {
}
