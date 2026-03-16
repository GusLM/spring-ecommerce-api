package com.gusdev.spring_ecommerce_api.entities.resources;

import com.gusdev.spring_ecommerce_api.dto.PageResponse;
import com.gusdev.spring_ecommerce_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gusdev.spring_ecommerce_api.entities.User;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Classe responsável por expor os endpoints REST relacionados à entidade "User".
 * Numa arquitetura em camadas, essa classe pertence à camada de "Resource"
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
	 * definido na classe.
	 * 
	 * Exemplo de chamada: GET http://localhost:8080/users
	 */
	@GetMapping
	public ResponseEntity<PageResponse<User>> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
	) {
		Page<User> result = userService.findAll(page, size);
		return ResponseEntity.ok().body(PageResponse.from(result));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}