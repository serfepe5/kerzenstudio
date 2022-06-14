package com.tfg.kerzenstudio.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tfg.kerzenstudio.enums.Rol;

@Entity
public class Usuario implements UserDetails {

	//ATRIBUTOS
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String user;
	private String password;
	private String correo;
	@Enumerated(EnumType.STRING)
	private Rol rol;
	
	
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos =new ArrayList<>();
	
	//CONSTRUCTORES
	public Usuario() {

	}

	/**
	 * @param id
	 * @param usuario
	 * @param password
	 * @param rol
	 */
	public Usuario(Long id, String user, String password, Rol rol) {
		super();
		this.id = id;
		this.user = user;
		this.password = password;
		this.rol = rol;
	}
	
	
	
	public Usuario(Long id, String user, String password, String correo, Rol rol) {
		super();
		this.id = id;
		this.user = user;
		this.password = password;
		this.correo = correo;
		this.rol = rol;
	}

	
	//METODOS, GETTERS Y SETTERS
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(rol.toString()));
		return roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String getUsername() {
		return user;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	

}
