package com.gusdev.spring_ecommerce_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gusdev.spring_ecommerce_api.entities.User;

/**
 * Interface responsável pela camada de acesso a dados (Data Access Layer) da
 * entidade User.
 * 
 * Ao estender JpaRepository, essa interface herda automaticamente diversos
 * métodos prontos para operações de CRUD (Create, Read, Update, Delete), sem a
 * necessidade de implementação manual.
 * 
 * O Spring Data JPA gera a implementação dessa interface em tempo de execução.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * JpaRepository<User, Long>
	 * 
	 * O primeiro parâmetro (User) indica qual entidade será gerenciada.
	 * 
	 * O segundo parâmetro (Long) indica o tipo da chave primária (ID) da entidade
	 * User.
	 * 
	 * Métodos herdados automaticamente incluem: - save() - saveAll() - findById() -
	 * findAll() - deleteById() - delete() - count()
	 * 
	 * Além disso, é possível criar métodos personalizados apenas declarando sua
	 * assinatura, por exemplo:
	 * 
	 * Optional<User> findByEmail(String email);
	 * 
	 * O Spring interpretará o nome do método e gerará a consulta automaticamente.
	 */

}