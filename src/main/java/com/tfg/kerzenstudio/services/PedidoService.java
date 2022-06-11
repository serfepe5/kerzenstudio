package com.tfg.kerzenstudio.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.kerzenstudio.model.Pedido;
import com.tfg.kerzenstudio.repositories.PedidoRepository;

@Service
@Transactional
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	//TODAS LOS PEDIDOS
	public List<Pedido> listAll() {
		List<Pedido> pedidos = repo.findAll();
		return pedidos;
	}

	//CARRITO POR REFERENCIA
	public Pedido findPrenda(Long id) {
		Optional<Pedido> pedido = repo.findById(id);
		if (pedido.isPresent()) {
			return pedido.get();
		}
		return null;
	}

	
	//GUARDAR CARRITO 
	public Pedido save(Pedido pedido) {
		return repo.save(pedido);

	}

	
	//BORRAR CARRITO
	public void delete(Long id) {
		repo.deleteById(id);
	}


}