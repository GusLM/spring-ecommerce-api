package com.gusdev.spring_ecommerce_api.config;

import java.util.Arrays;

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
	}
}