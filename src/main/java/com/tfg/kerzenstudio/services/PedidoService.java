package com.tfg.kerzenstudio.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tfg.kerzenstudio.model.Pedido;
import com.tfg.kerzenstudio.model.Producto;
import com.tfg.kerzenstudio.model.Usuario;
import com.tfg.kerzenstudio.repositories.PedidoRepository;
import com.tfg.kerzenstudio.repositories.ProductoRepository;
import com.tfg.kerzenstudio.repositories.UsuarioRepository;

@Service
@Transactional
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private ProductoRepository repoproducto;

	@Autowired
	private CarritoService carritoservice;
	
	@Autowired
	private UsuarioRepository repousuario;
	

	//TODAS LOS PEDIDOS
	public List<Pedido> listAll() {
		List<Pedido> pedidos = repo.findAll();
		return pedidos;
	}
	//PEDIDO POR REFERENCIA
	public Pedido findPedido(Long id) {
		Optional<Pedido> pedido = repo.findById(id);
		if (pedido.isPresent()) {
			return pedido.get();
		}
		return null;
	}
	
	//PEDIDO POR REFERENCIA
		public List<Pedido> findPedidoUser() {
			Object usuario = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String usu =((UserDetails) usuario).getUsername();
			Optional<Usuario> user = repousuario.findByUser(usu);
			List<Pedido> pedidos = repo.findIdUser(user.get().getId());
			return pedidos;
		}
	//GUARDAR PEDIDO 
	public Pedido save(Pedido pedido) {
		return repo.save(pedido);
	}
	
	public Pedido crearPedido(Map<Producto, Integer> carrito) {
		Pedido pedido = new Pedido();
		List<Producto> productos= new ArrayList<>();
		for(Entry<Producto, Integer> producto : carrito.entrySet()) {
			for (int i = 0; i < producto.getValue(); i++) {
				productos.add(producto.getKey());
				Optional<Producto> p = repoproducto.findById(producto.getKey().getId());
				if(p.isPresent()) {
					Producto pd = p.get();
					pd.setStock(pd.getStock()-1);
					repoproducto.save(pd);
				}
			}
		}
		pedido.setProductos(productos);
		pedido.setFechapedido(new Date());
		Object usuario = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usu =((UserDetails) usuario).getUsername();
		Optional<Usuario> user = repousuario.findByUser(usu);
		pedido.setUsuario(user.get());
		pedido.setPrecioPedido(precioTotalCarrito(carrito) );
		carritoservice.vaciarCarrito();
		repo.save(pedido);
		return pedido;
	}
	
	private Float precioTotalCarrito(Map<Producto, Integer> carrito) {
		float precio = 0;
		for(Entry<Producto, Integer> producto : carrito.entrySet()) {
			precio += producto.getKey().getPrecio()*producto.getValue();
		}
		return precio;
	}
	
	


}