package com.tfg.kerzenstudio.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.tfg.kerzenstudio.model.Producto;
import com.tfg.kerzenstudio.repositories.ProductoRepository;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarritoService {

	private Map<Producto, Integer> carrito = new HashMap<>();

	@Autowired
	public ProductoRepository productorepository;

	public void addCarrito(Long id) {
		Optional<Producto> producto = productorepository.findById(id);
		if (producto.isPresent()) {
			if (carrito.containsKey(producto.get())) {
				carrito.replace(producto.get(), carrito.get(producto.get()) + 1);
			}else {
				carrito.put(producto.get(), 1);
			}
			precioTotalCarrito();
		}
	}

	public void removeItemCarrito(Long id) {
		Optional<Producto> producto = productorepository.findById(id);
		if (producto.isPresent()) {
			if (carrito.get(producto.get()) > 1) {
				carrito.replace(producto.get(), carrito.get(producto.get()) - 1);
			} else if (carrito.get(producto.get()) == 1) {
				carrito.remove(producto.get());
			}
			precioTotalCarrito();
		}
	}

	public void vaciarCarrito() {
		carrito.clear();
	}

	public Map<Producto, Integer> getCarrito() {
		return carrito;
	}
	
	public Float precioTotalCarrito() {
		float precio = 0;
		for(Entry<Producto, Integer> producto : carrito.entrySet()) {
			precio += producto.getKey().getPrecio()*producto.getValue();
		}
		return precio;
	}
}
