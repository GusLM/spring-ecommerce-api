package com.gusdev.spring_ecommerce_api.entities.resources;

import com.gusdev.spring_ecommerce_api.dto.PageResponse;
import com.gusdev.spring_ecommerce_api.entities.Order;
import com.gusdev.spring_ecommerce_api.entities.User;
import com.gusdev.spring_ecommerce_api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Classe responsável por expor os endpoints REST relacionados à entidade "Order".
 * Numa arquitetura em camadas, essa classe pertence à camada de "Resource"
 * (ou Controller), sendo responsável por receber requisições HTTP e retornar
 * respostas ao cliente.
 */
@RestController
@RequestMapping(value = "/orders")
// Define o caminho base para todos os endpoints desta classe.
// Todas as requisições começarão com /Orders
public class OrderResource {

	@Autowired
	private OrderService orderService;

	/**
	 * Endpoint HTTP GET que responde no caminho "/Orders".
	 * 
	 * @GetMapping sem parâmetro indica que ele responde exatamente ao caminho base
	 * definido na classe.
	 * 
	 * Exemplo de chamada: GET http://localhost:8080/orders
	 */
	@GetMapping
	public ResponseEntity<PageResponse<Order>> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
	) {
		Page<Order> result = orderService.findAll(page, size);
		return ResponseEntity.ok().body(PageResponse.from(result));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order obj = orderService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}