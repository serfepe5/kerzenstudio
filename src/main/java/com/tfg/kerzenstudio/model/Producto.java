package com.tfg.kerzenstudio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.tfg.kerzenstudio.enums.Talla;
import com.tfg.kerzenstudio.enums.Tipo;

@Entity
@Table(name = "productos")
public class Producto{
	
	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	private String imgUrl;
	
	@Enumerated(EnumType.STRING)
	private Talla talla;
	
	private float precio;
	
	
	@ManyToMany
    @JoinTable(name = "carritoproductos", 
        joinColumns = { @JoinColumn(name = "idproducto") }, 
        inverseJoinColumns = { @JoinColumn(name = "idcarrito") })
	private List<Carrito> carritos = new ArrayList<>();
	
	
	//CONSTRUCTORES
	public Producto() {
		
	}
	
	
	public Producto(Long idproducto, Talla talla, float precio) {
		this.id = idproducto;
		this.talla = talla;
		this.precio = precio;
	}






	//GETTERS Y SETTERS
	public Long getId() {
		return id;
	}


	public void setIdproducto(Long idproducto) {
		this.id = idproducto;
	}


	public Talla getTalla() {
		return talla;
	}


	public void setTalla(Talla talla) {
		this.talla = talla;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}




	public List<Carrito> getCarritos() {
		return carritos;
	}


	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}
	
	
	
	
	

}
