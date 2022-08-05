package com.generation.lojagames.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

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

import com.generation.lojagames.model.Produtos;
import com.generation.lojagames.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produtos>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produtos> GetById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());		
	}
	
	@GetMapping("/preco_incial/{inicio}/preco_final/{fim}")
	public ResponseEntity<List<Produtos>> getByPrecoEntre(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim){
		return ResponseEntity.ok(repository.findAllByPrecoBetween(inicio, fim)); 
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Produtos>> GetByIdTipo(@PathVariable String tipo){
		return ResponseEntity.ok(repository.findAllByTipoContainingIgnoreCase(tipo));
	}
	
	@PostMapping
	public ResponseEntity<Produtos> post (@Valid@RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtos));
	}
	
	@PutMapping
	public ResponseEntity<Produtos> put (@RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produtos));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id){
		repository.deleteById(id);
	}
}
