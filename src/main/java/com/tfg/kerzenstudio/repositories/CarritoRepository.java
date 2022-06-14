package com.tfg.kerzenstudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.kerzenstudio.model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

}