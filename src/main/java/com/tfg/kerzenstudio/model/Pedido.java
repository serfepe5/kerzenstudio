package com.tfg.kerzenstudio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "pedidos")
public class Pedido {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
    @JoinTable(name = "pedidoproductos", 
        joinColumns = { @JoinColumn(name = "idproducto") }, 
        inverseJoinColumns = { @JoinColumn(name = "idpedido") })
	private List<Producto> productos = new ArrayList<>();
	
	


	float precioPedido;

	private Date fechapedido;
	
	private String direccionentrega;



	@ManyToOne
	@JoinColumn(name = "idusuario")
	private Usuario usuario;

	// CONSTRUCTORES
	public Pedido() {

	}

	public Pedido(Long idpedido, float precioPedido, Date fechapedido, String direccionentrega) {
		this.id = idpedido;
		this.precioPedido = precioPedido;
		this.fechapedido = fechapedido;
		this.direccionentrega = direccionentrega;
	}

	// GETTERS Y SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long idpedido) {
		this.id = idpedido;
	}

	public float getPrecioPedido() {
		return precioPedido;
	}

	public void setPrecioPedido(float precioPedido) {
		this.precioPedido = precioPedido;
	}

	public Date getFechapedido() {
		return fechapedido;
	}

	public void setFechapedido(Date fechapedido) {
		this.fechapedido = fechapedido;
	}

	

	public String getDireccionentrega() {
		return direccionentrega;
	}

	public void setDireccionentrega(String direccionentrega) {
		this.direccionentrega = direccionentrega;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	

}
