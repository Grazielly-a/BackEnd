package com.generation.farmagen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.farmagen.model.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
	
	public List<Categorias> findAllByCategoriasContainingIgnoreCase (String categorias);

}
