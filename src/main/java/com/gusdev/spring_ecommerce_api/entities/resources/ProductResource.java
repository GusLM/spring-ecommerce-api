package com.gusdev.spring_ecommerce_api.entities.resources;

import com.gusdev.spring_ecommerce_api.dto.PageResponse;
import com.gusdev.spring_ecommerce_api.entities.Product;
import com.gusdev.spring_ecommerce_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Classe responsável por expor os endpoints REST relacionados à entidade "Product".
 * Numa arquitetura em camadas, essa classe pertence à camada de "Resource"
 * (ou Controller), sendo responsável por receber requisições HTTP e retornar
 * respostas ao cliente.
 */
@RestController
@RequestMapping(value = "/products")
// Define o caminho base para todos os endpoints desta classe.
// Todas as requisições começarão com /products
public class ProductResource {

	@Autowired
	private ProductService productService;

	/**
	 * Endpoint HTTP GET que responde no caminho "/products".
	 * 
	 * @GetMapping sem parâmetro indica que ele responde exatamente ao caminho base
	 * definido na classe.
	 * 
	 * Exemplo de chamada: GET http://localhost:8080/products
	 */
	@GetMapping
	public ResponseEntity<PageResponse<Product>> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
	) {
		Page<Product> result = productService.findAll(page, size);
		return ResponseEntity.ok().body(PageResponse.from(result));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product obj = productService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}