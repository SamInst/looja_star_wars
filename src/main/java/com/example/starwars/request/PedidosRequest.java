package com.example.starwars.request;

public record PedidosRequest(
        Client cliente,
        Products produto,
        Float total,
        String rentabilidade

) {
    public record Client(
            String nome
    ){}
    public record Products(
            String name,
            Float precoUnidade,
            Float multiplicador
    ){}
}
