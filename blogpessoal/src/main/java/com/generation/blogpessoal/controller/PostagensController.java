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

import com.generation.blogpessoal.model.Postagens;
import com.generation.blogpessoal.repository.PostagensRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class PostagensController {
	
	@Autowired
	private PostagensRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagens>> GetAll () 
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagens> GetById(@PathVariable Long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagens>> GetByTitulo(@PathVariable String titulo)
	{
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagens> post (@RequestBody Postagens postagens)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagens));
	}
	
	@PutMapping
	public ResponseEntity<Postagens> put (@RequestBody Postagens postagens)
	{
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagens));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id)
	{
		repository.deleteById(id);
	}

}
