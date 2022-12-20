package com.example.starwars.services;

import com.example.starwars.exceptions.EntityNotFound;
import com.example.starwars.exceptions.InvalidParam;
import com.example.starwars.model.Client;
import com.example.starwars.model.Pedidos;
import com.example.starwars.model.Products;
import com.example.starwars.repository.PedidosRepository;
import com.example.starwars.request.PedidosRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidosService {

    private final PedidosRepository pedidosRepository;

    private Products products;



//    private Client client;

    public PedidosService(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }
    public ResponseEntity<List<PedidosRequest>> findByIdPedidos (Long id_cliente, Long id_produto, Integer amount, Float offer) {
        List<Pedidos> pedidosRequestList = new ArrayList<>(pedidosRepository.queryAllByClient_IdAndProducts_Id(id_cliente, id_produto));
        return getListRequestPedidos(amount,offer,pedidosRequestList);
    }

    public ResponseEntity<List<PedidosRequest>> findByPrecoUnitPedidos (Long id_cliente, Long id_produto, Integer amount, Float offer, Float unidade) {
        List<Pedidos> pedidosRequestList = new ArrayList<>(pedidosRepository.queryAllByClient_IdAndProducts_Id(id_cliente, id_produto));
        return getPedidosAlterarPrecoUnitario(amount,offer,pedidosRequestList, unidade);
    }

    private ResponseEntity<List<PedidosRequest>> getListRequestPedidos(Integer amount, Float offer, List<Pedidos> pedidos){
        List<PedidosRequest> pedidosRequestList = new ArrayList<>();

        pedidos.forEach(pedidos1-> {

            var multiplier = amount * pedidos1.getProducts().getMult();
            var total = multiplier * pedidos1.getProducts().getPriceUnit();
            String rentabilidade = null;
            float tirarDezPorcento = (float) (total * 0.1);
            float valorPorcentagem = total - tirarDezPorcento;

            if (offer > total) {
                rentabilidade = "Obteve uma excelente rentabilidade";
            } else if (offer <= total && offer >= valorPorcentagem) {
                rentabilidade = "Obeteve uma boa rentabilidade";
            } else if (offer < valorPorcentagem) {
                rentabilidade = "Obteve uma rentabilidade ruim";
            }
            if (amount <= 0) {
                throw new EntityNotFound("a quantidade não pode ser menor ou igual a zero");
            }
            pedidosRequestList.add(new PedidosRequest(
                    new PedidosRequest.Client(
                            pedidos1.getClient().getName()),
                    new PedidosRequest.Products(
                            pedidos1.getProducts().getName(),
                            pedidos1.getProducts().getPriceUnit(),
                            pedidos1.getProducts().getMult()),
                    total,
                    rentabilidade
            ));
        });
        return ResponseEntity.ok(pedidosRequestList);
}

    private ResponseEntity<List<PedidosRequest>> getPedidosAlterarPrecoUnitario(Integer amount, Float offer, List<Pedidos> pedidos, Float unidade){
        List<PedidosRequest> pedidosRequestList = new ArrayList<>();

        pedidos.forEach(pedidos1-> {

            var multiplier = amount * pedidos1.getProducts().getMult();
            var total = multiplier * unidade;


            String rentabilidade = null;
            float tirarDezPorcento = (float) (total * 0.1);
            float valorPorcentagem = total - tirarDezPorcento;

            if (offer > total) {
                rentabilidade = "Obteve uma excelente rentabilidade";
            } else if (offer <= total && offer >= valorPorcentagem) {
                rentabilidade = "Obeteve uma boa rentabilidade";
            } else if (offer < valorPorcentagem) {
                rentabilidade = "Obteve uma rentabilidade ruim";
            }
            if (amount <= 0) {
                throw new InvalidParam("a quantidade não pode ser menor ou igual a zero");
            }

            pedidosRequestList.add(new PedidosRequest(
                    new PedidosRequest.Client(
                            pedidos1.getClient().getName()),
                    new PedidosRequest.Products(
                            pedidos1.getProducts().getName(),
                            unidade,
                            pedidos1.getProducts().getMult()),
                    total,
                    rentabilidade
            ));
        });
        return ResponseEntity.ok(pedidosRequestList);
    }
}
