package com.tfg.kerzenstudio.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tfg.kerzenstudio.enums.Estado;
import com.tfg.kerzenstudio.enums.MetodoPago;

@Entity
@Table(name = "pedidos")
public class Pedido {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy="pedido")
	private Carrito carrito;
	
	float precioPedido;

	private Date fechapedido;

	private Date fechaentrega;

	private String direccionentrega;

	private MetodoPago metodopago;

	private Estado estado;

	// CONSTRUCTORES
	public Pedido() {

	}

	public Pedido(Long idpedido,float precioPedido, Date fechapedido, Date fechaentrega,
			String direccionentrega, MetodoPago metodopago, Estado estado) {
		this.id = idpedido;
		this.precioPedido = precioPedido;
		this.fechapedido = fechapedido;
		this.fechaentrega = fechaentrega;
		this.direccionentrega = direccionentrega;
		this.metodopago = metodopago;
		this.estado = estado;
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

	public Date getFechaentrega() {
		return fechaentrega;
	}

	public void setFechaentrega(Date fechaentrega) {
		this.fechaentrega = fechaentrega;
	}

	public String getDireccionentrega() {
		return direccionentrega;
	}

	public void setDireccionentrega(String direccionentrega) {
		this.direccionentrega = direccionentrega;
	}

	public MetodoPago getMetodopago() {
		return metodopago;
	}

	public void setMetodopago(MetodoPago metodopago) {
		this.metodopago = metodopago;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	

}
