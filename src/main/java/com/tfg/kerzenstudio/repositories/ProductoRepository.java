package com.tfg.kerzenstudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.kerzenstudio.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}