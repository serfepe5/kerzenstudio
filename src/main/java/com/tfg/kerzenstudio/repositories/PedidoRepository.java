package com.tfg.kerzenstudio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tfg.kerzenstudio.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query(value = "SELECT p FROM Pedido p WHERE p.usuario.id=?1")
	List<Pedido> findIdUser(Long id);


}