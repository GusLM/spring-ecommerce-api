package com.gusdev.spring_ecommerce_api.entities.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusdev.spring_ecommerce_api.entities.User;

/**
 * Classe responsável por expor os endpoints REST relacionados à entidade User.
 * 
 * Em uma arquitetura em camadas, essa classe pertence à camada de "Resource"
 * (ou Controller), sendo responsável por receber requisições HTTP e retornar
 * respostas ao cliente.
 */
@RestController
@RequestMapping(value = "/users")
// Define o caminho base para todos os endpoints desta classe.
// Todas as requisições começarão com /users
public class UserResource {

	/**
	 * Endpoint HTTP GET que responde no caminho "/users".
	 * 
	 * @GetMapping sem parâmetro indica que ele responde exatamente ao caminho base
	 *             definido na classe.
	 * 
	 *             Exemplo de chamada: GET http://localhost:8080/users
	 */
	@GetMapping
	public ResponseEntity<User> findAll() {

		// Criação manual de um objeto User apenas para teste.
		// Em um cenário real, os dados viriam do banco de dados
		// através de uma camada de serviço.
		User user = new User(1L, "Gustavo Santos", "gustavo@mail.com", "99999999", "12345");

		/**
		 * ResponseEntity representa a resposta HTTP completa.
		 * 
		 * O método ok() define o status HTTP 200 (OK). O método body(user) define o
		 * corpo da resposta que será automaticamente convertido para JSON pelo Spring.
		 */
		return ResponseEntity.ok().body(user);
	}
}