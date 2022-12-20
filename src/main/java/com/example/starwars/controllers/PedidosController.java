package com.example.starwars.controllers;

import com.example.starwars.model.Pedidos;
import com.example.starwars.repository.PedidosRepository;
import com.example.starwars.request.PedidosRequest;
import com.example.starwars.services.PedidosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {
    private final PedidosService pedidosService;
    private final PedidosRepository pedidosRepository;

    public PedidosController(PedidosService pedidosService, PedidosRepository pedidosRepository) {
        this.pedidosService = pedidosService;
        this.pedidosRepository = pedidosRepository;
    }

    @GetMapping
    public List<Pedidos> list(){return pedidosRepository.findAll();}
    @GetMapping("/queryPedidos")
    public ResponseEntity<List<PedidosRequest>> findPedidos (Long id_cliente, Long id_produto, Integer amount, Float offer){
        return pedidosService.findByIdPedidos(id_cliente, id_produto, amount, offer);
    }

    @GetMapping("/queryPedidosByUnit")
    public ResponseEntity<List<PedidosRequest>> findPedidosByUnit (Long id_cliente, Long id_produto, Integer amount, Float offer, Float preco_unidade){
        return pedidosService.findByPrecoUnitPedidos(id_cliente, id_produto, amount, offer, preco_unidade);
    }
}