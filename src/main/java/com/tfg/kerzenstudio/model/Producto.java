package com.tfg.kerzenstudio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	private String imgUrl;
	
	@Enumerated(EnumType.STRING)
	private Talla talla;
	
	private float precio;
	
	private int stock;
	
	
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
	
	

	
	
	public Producto(Long id, String nombre, Tipo tipo, String imgUrl, Talla talla, float precio) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.imgUrl = imgUrl;
		this.talla = talla;
		this.precio = precio;
	}
	
	


	public Producto(Long id, String nombre, Tipo tipo, String imgUrl, Talla talla, float precio, int stock,
			List<Carrito> carritos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.imgUrl = imgUrl;
		this.talla = talla;
		this.precio = precio;
		this.stock = stock;
		this.carritos = carritos;
	}


	//GETTERS Y SETTERS

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
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


	public List<Carrito> getCarritos() {
		return carritos;
	}


	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(id, other.id);
	}
	
	






	
	
	
	
	

}
