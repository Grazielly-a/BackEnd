package com.generation.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
	
	public Optional<Usuarios> findByUsuario(String usuario);
	
	public List <Usuarios> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
