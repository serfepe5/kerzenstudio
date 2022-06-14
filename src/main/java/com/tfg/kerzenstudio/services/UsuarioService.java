package com.tfg.kerzenstudio.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tfg.kerzenstudio.enums.Rol;
import com.tfg.kerzenstudio.model.Usuario;
import com.tfg.kerzenstudio.repositories.UsuarioRepository;

@Service
@Transactional
public class UsuarioService implements UserDetailsService {


	@Autowired
	private UsuarioRepository repo;
	

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Optional<Usuario> user = repo.findByUser(username);
		User usu = null;
		if (!user.isPresent()) {
            throw new UsernameNotFoundException("No existe un usuario con el siguiente email: " + username);
        } else {
            Usuario usuario = user.get();
            Rol rol = usuario.getRol();
            Set<GrantedAuthority> ga = new HashSet<>();
            ga.add(new SimpleGrantedAuthority(rol.toString()));
            usu = new User(username, usuario.getPassword(), ga);

        }
		return usu;
	} 
	
	public Optional<Usuario> buscarusuario(String username)
	{
		return repo.findByUser(username);
	}	

	public void save(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		repo.save(usuario);
	}


}