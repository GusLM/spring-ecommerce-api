package com.gusdev.spring_ecommerce_api.entities.resources;

import com.gusdev.spring_ecommerce_api.dto.PageResponse;
import com.gusdev.spring_ecommerce_api.entities.Category;
import com.gusdev.spring_ecommerce_api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Classe responsável por expor os endpoints REST relacionados à entidade "Category".
 * Numa arquitetura em camadas, essa classe pertence à camada de "Resource"
 * (ou Controller), sendo responsável por receber requisições HTTP e retornar
 * respostas ao cliente.
 */
@RestController
@RequestMapping(value = "/categories")
// Define o caminho base para todos os endpoints desta classe.
// Todas as requisições começarão com /categories
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	/**
	 * Endpoint HTTP GET que responde no caminho "/categories".
	 * 
	 * @GetMapping sem parâmetro indica que ele responde exatamente ao caminho base
	 * definido na classe.
	 * 
	 * Exemplo de chamada: GET http://localhost:8080/categories
	 */
	@GetMapping
	public ResponseEntity<PageResponse<Category>> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
	) {
		Page<Category> result = categoryService.findAll(page, size);
		return ResponseEntity.ok().body(PageResponse.from(result));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category obj = categoryService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}