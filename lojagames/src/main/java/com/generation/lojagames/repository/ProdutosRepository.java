package com.generation.lojagames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.lojagames.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
	
	public List<Produtos> findAllByPrecoBetween(@Param("inicio") BigDecimal inicio, @Param("fim") BigDecimal fim); 
	
	public List<Produtos> findAllByTipoContainingIgnoreCase (@Param ("tipo") String tipo);

}
