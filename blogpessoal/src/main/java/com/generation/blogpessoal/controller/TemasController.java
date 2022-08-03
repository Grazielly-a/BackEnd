package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Temas;
import com.generation.blogpessoal.repository.TemasRepository;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class TemasController {
	
	@Autowired
	private TemasRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Temas>> GetAll () 
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Temas> GetById(@PathVariable Long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descrição/{descrição}")
	public ResponseEntity<List<Temas>> GetByDescricao(@PathVariable String descricao)
	{
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Temas> post (@RequestBody Temas temas)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(temas));
	}
	
	@PutMapping
	public ResponseEntity<Temas> put (@RequestBody Temas temas)
	{
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(temas));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id)
	{
		repository.deleteById(id);
	}

}
