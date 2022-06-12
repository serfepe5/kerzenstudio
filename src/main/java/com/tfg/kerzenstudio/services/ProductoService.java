package com.tfg.kerzenstudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.kerzenstudio.enums.Tipo;
import com.tfg.kerzenstudio.model.Producto;
import com.tfg.kerzenstudio.repositories.ProductoRepository;

@Service
@Transactional
public class ProductoService {

	@Autowired
	private ProductoRepository repo;

	//TODAS LOS PRODUCTOS
	public List<Producto> listAll() {
		List<Producto> productos = repo.findAll();
		return productos;
	}

	
	//PRODUCTO POR REFERENCIA
	public Producto findproductos(Long id) {
		Optional<Producto> producto = repo.findById(id);
		if (producto.isPresent()) {
			return producto.get();
		}
		return null;
	}
	
	//PRODUCTO POR TIPO
	public List<Producto> findProductoTipo(Tipo tipo) {
		List<Producto> productos = repo.findAll();
		List<Producto> productostipo = new ArrayList<Producto>();
		for(Producto p : productos) {
			if(p.getTipo()==tipo) {
				productostipo.add(p);
			}
		}
		return productostipo;
	}

	
	//GUARDAR PRODUCTO
	public Producto save(Producto producto) {
		return repo.save(producto);

	}

	
	//BORRAR PRODUCTO
	public void delete(Long id) {
		repo.deleteById(id);
	}


}