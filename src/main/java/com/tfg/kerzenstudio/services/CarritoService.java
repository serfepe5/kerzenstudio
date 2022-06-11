package com.tfg.kerzenstudio.services;

import java.util.Optional;

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


	//CARRITO POR REFERENCIA
	public Carrito findPrenda(Long id) {
		Optional<Carrito> carrito = repo.findById(id);
		if (carrito.isPresent()) {
			return carrito.get();
		}
		return null;
	}

	
	//GUARDAR CARRITO 
	public Carrito save(Carrito carrito) {
		return repo.save(carrito);

	}

	
	//BORRAR CARRITO
	public void delete(Long id) {
		repo.deleteById(id);
	}


}