package com.gusdev.spring_ecommerce_api.entities.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusdev.spring_ecommerce_api.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<User> findAll() {
		User user = new User(1L, "Gustavo Santos", "gustavo@mail.com", "99999999", "12345");
		return ResponseEntity.ok().body(user);
	}
}
