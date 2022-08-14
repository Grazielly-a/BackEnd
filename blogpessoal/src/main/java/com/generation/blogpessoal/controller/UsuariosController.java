package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Usuarios;
import com.generation.blogpessoal.model.UsuariosLogin;
import com.generation.blogpessoal.repository.UsuariosRepository;
import com.generation.blogpessoal.service.UsuariosService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuariosController {
	
	@Autowired
	private UsuariosService service;
	
	@Autowired
	private UsuariosRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity <List<Usuarios>> GetAll() {		
		return ResponseEntity.ok(repository.findAll());		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuarios> GetById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuariosLogin> login(@RequestBody Optional<UsuariosLogin> usuariosLogin) {
		return service.autenticarUsuario(usuariosLogin).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());		
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuarios> postUsuarios(@Valid @RequestBody Usuarios usuario) {
		return service.cadastrarUsuario(usuario).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp)).orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuarios> putUsuarios(@Valid @RequestBody Usuarios usuario) {
		return service.atualizarUsuario(usuario).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}	
	

}
