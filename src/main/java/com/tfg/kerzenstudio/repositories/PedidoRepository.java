package com.tfg.kerzenstudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.kerzenstudio.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}