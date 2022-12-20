package com.example.starwars.repository;

import com.example.starwars.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository <Pedidos, Long> {

    List<Pedidos> queryAllByClient_IdAndProducts_Id(Long client_id, Long products_id);
}
