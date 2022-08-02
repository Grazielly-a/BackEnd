package com.generation.blogpessoal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.blogpessoal.model.Postagens;

@Repository
public interface PostagensRepository extends JpaRepository<Postagens, Long>{
	
	public List<Postagens> findAllByTituloContainingIgnoreCase (String titulo);
}
