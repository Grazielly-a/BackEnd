package com.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.blogpessoal.model.Usuarios;
import com.generation.blogpessoal.repository.UsuariosRepository;
import com.generation.blogpessoal.service.UsuariosService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuariosControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuariosService service;

	@Autowired
	private UsuariosRepository repository;

	@BeforeAll
	void start() {

		repository.deleteAll();

		service.cadastrarUsuario(new Usuarios(0L, "Root", "Root@root.com", "rootroot", " "));
	}

	@Test
	@DisplayName("Cadastrar um usuário")
	public void deveCadastrarUmUsuario() {
		HttpEntity<Usuarios> corpoRequisicao = new HttpEntity<Usuarios>(new Usuarios(0L, "Paulo Antunes",
				"paulo_antunes@email.com.br", "13465278", "https://i.imgur.com/JR7kUFU.jpg"));

		ResponseEntity<Usuarios> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST,
				corpoRequisicao, Usuarios.class);

		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
		
		assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());
		
		assertEquals(corpoRequisicao.getBody().getUsuario(), corpoResposta.getBody().getUsuario());
	}

	@Test
	@DisplayName("Não deve permitir duplicação do usuário")
	public void naoDeveDuplicarUsuario() {

		service.cadastrarUsuario(new Usuarios(0L, "Maria da Silva", "maria_silva@email.com.br", "13465278",
				"https://i.imgur.com/T12NIp9.jpg"));

		HttpEntity<Usuarios> corpoRequisicao = new HttpEntity<Usuarios>(new Usuarios(0L, "Maria da Silva",
				"maria_silva@email.com.br", "13465278", "https://i.imgur.com/T12NIp9.jpg"));

		ResponseEntity<Usuarios> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST,
				corpoRequisicao, Usuarios.class);

		assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("Atualizar um usuário")
	public void deveAtualizarUmUsuario() {

		Optional<Usuarios> usuarioCadastrado = service.cadastrarUsuario(new Usuarios(0L, "Juliana Andrews",
				"juliana_andrews@email.com.br", "juliana123", "https://i.imgur.com/yDRVeK7.jpg"));

		Usuarios usuarioUpdate = new Usuarios(usuarioCadastrado.get().getId(), "Juliana Andrews Ramos",
				"juliana_ramos@email.com.br", "juliana123", "https://i.imgur.com/yDRVeK7.jpg");

		HttpEntity<Usuarios> corpoRequisicao = new HttpEntity<Usuarios>(usuarioUpdate);

		ResponseEntity<Usuarios> corpoResposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Usuarios.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());

		assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());

		assertEquals(corpoRequisicao.getBody().getUsuario(), corpoResposta.getBody().getUsuario());
	}

	@Test
	@DisplayName("Listar todos os usuários")
	public void deveMostrarTodosUsuarios() {

		service.cadastrarUsuario(new Usuarios(0L, "Sabrina Sanches", "sabrina_sanches@email.com.br", "sabrina123",
				"https://i.imgur.com/5M2p5Wb.jpg"));

		service.cadastrarUsuario(new Usuarios(0L, "Ricardo Marques", "ricardo_marques@email.com.br", "ricardo123",
				"https://i.imgur.com/Sk5SjWE.jpg"));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}

}
