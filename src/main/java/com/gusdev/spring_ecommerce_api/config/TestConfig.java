package com.gusdev.spring_ecommerce_api.config;

import java.time.Instant;
import java.util.Arrays;

import com.gusdev.spring_ecommerce_api.entities.Category;
import com.gusdev.spring_ecommerce_api.entities.Order;
import com.gusdev.spring_ecommerce_api.entities.enums.OrderStatus;
import com.gusdev.spring_ecommerce_api.repositories.CategoryRepository;
import com.gusdev.spring_ecommerce_api.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gusdev.spring_ecommerce_api.entities.User;
import com.gusdev.spring_ecommerce_api.repositories.UserRepository;

/**
 * Classe de configuração responsável por popular o banco de dados com dados
 * iniciais de teste quando a aplicação é iniciada.
 * 
 * Esta classe será carregada apenas quando o perfil "test" estiver ativo.
 */
@Configuration
@Profile("test") // Garante que essa configuração execute apenas no ambiente de teste
public class TestConfig implements CommandLineRunner {

	/**
	 * Injeção de dependência do UserRepository.
	 * 
	 * O Spring fornece automaticamente a implementação em tempo de execução, pois
	 * UserRepository é um repositório do Spring Data JPA.
	 */
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	/**
	 * Método executado automaticamente após o contexto da aplicação Spring ser
	 * completamente inicializado.
	 * 
	 * A interface CommandLineRunner é comumente utilizada para: - Inserir dados
	 * iniciais (seed) - Executar lógica de inicialização - Rodar rotinas no startup
	 * da aplicação
	 */
	@Override
	public void run(String... args) throws Exception {

		// Criando objetos User para teste
		// O id está como null porque será gerado automaticamente pelo banco de dados
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		// Persiste ambos os usuários no banco em uma única operação
		// saveAll() salva todos os objetos presentes na coleção informada
		userRepository.saveAll(Arrays.asList(u1, u2));

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.DELIVERED, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.SHIPPED,u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT,u1);

		// Persiste todos os pedidos no banco em uma única operação
		// saveAll() salva todos os objetos presentes na coleção informada
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
	}
}