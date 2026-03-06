package com.gusdev.spring_ecommerce_api.entities.resources;

import com.gusdev.spring_ecommerce_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusdev.spring_ecommerce_api.entities.User;

import java.util.List;

/**
 * Classe responsável por expor os endpoints REST relacionados à entidade User.
 * Em uma arquitetura em camadas, essa classe pertence à camada de "Resource"
 * (ou Controller), sendo responsável por receber requisições HTTP e retornar
 * respostas ao cliente.
 */
@RestController
@RequestMapping(value = "/users")
// Define o caminho base para todos os endpoints desta classe.
// Todas as requisições começarão com /users
public class UserResource {

	@Autowired
	private UserService userService;

	/**
	 * Endpoint HTTP GET que responde no caminho "/users".
	 * 
	 * @GetMapping sem parâmetro indica que ele responde exatamente ao caminho base
	 *             definido na classe.
	 * 
	 *             Exemplo de chamada: GET http://localhost:8080/users
	 */
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok().body(userService.findAll());
	}
}