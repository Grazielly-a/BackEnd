package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Temas;

@Repository
public interface TemasRepository extends JpaRepository<Temas, Long>{
	public List<Temas> findAllByDescricaoContainingIgnoreCase (String descricao);

}
