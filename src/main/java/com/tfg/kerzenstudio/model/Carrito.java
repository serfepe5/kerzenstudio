package com.tfg.kerzenstudio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carritos")
public class Carrito {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private float precioCarrito;
	
	@OneToOne
	@JoinColumn(name="idpedido")
	private Pedido pedido;
	
	@ManyToMany
    @JoinTable(name = "carritoproductos", 
        joinColumns = { @JoinColumn(name = "idcarrito") }, 
        inverseJoinColumns = { @JoinColumn(name = "idproducto") })
	private List<Producto> productos =new ArrayList<>();

	// CONSTRUCTORES
	public Carrito() {

	}

	public Carrito(Long id, List<Producto> productos, float precioCarrito) {
		this.id = id;
		this.productos = productos;
		this.precioCarrito = precioCarrito;
	}

	// GETTERS Y SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public float getPrecioCarrito() {
		return precioCarrito;
	}

	public void setPrecioCarrito(float precioCarrito) {
		this.precioCarrito = precioCarrito;
	}

}
