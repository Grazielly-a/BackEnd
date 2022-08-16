package com.generation.lojagames.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Usuarios;
import com.generation.lojagames.repository.UsuariosRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuariosRepository repository;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Usuarios> usuarios = repository.findByUsuario(userName);

		if (usuarios.isPresent())
			return new UserDetailsImpl(usuarios.get());
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);

	}

}
