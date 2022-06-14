package com.tfg.kerzenstudio.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.kerzenstudio.model.Carrito;
import com.tfg.kerzenstudio.repositories.CarritoRepository;

@Service
@Transactional
public class CarritoService {

	@Autowired
	private CarritoRepository repo;

	//GUARDAR CARRITO 
	public Carrito save(Carrito carrito) {
		return repo.save(carrito);

	}

	
	//BORRAR CARRITO
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	


}