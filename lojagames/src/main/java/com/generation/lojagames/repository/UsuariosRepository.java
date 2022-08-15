package com.generation.lojagames.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.lojagames.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
	
	public Optional<Usuarios> findByUsuario(String usuario);

}
