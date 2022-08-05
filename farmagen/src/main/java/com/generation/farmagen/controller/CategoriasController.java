package com.generation.farmagen.controller;

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

import com.generation.farmagen.model.Categorias;
import com.generation.farmagen.repository.CategoriasRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class CategoriasController {
	
	@Autowired
	private CategoriasRepository repository;
	
	@GetMapping 
	public ResponseEntity<List<Categorias>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categorias> GetById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categorias/{categrias}")
	public ResponseEntity<List<Categorias>> GetByIdCategorias(@PathVariable String categorias){
		return ResponseEntity.ok(repository.findAllByCategoriasContainingIgnoreCase(categorias));
	}
	
	@PostMapping
	public ResponseEntity<Categorias> post (@Valid@RequestBody Categorias categorias){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categorias));
	}
	
	@PutMapping
	public ResponseEntity<Categorias> put (@RequestBody Categorias categorias){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categorias));
	}
	
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id){
		repository.deleteById(id);
	}
			

}
