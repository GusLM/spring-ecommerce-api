package com.gusdev.spring_ecommerce_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    /*
     * Lado inverso da associação Many-to-Many entre Category e Product.
     *
     * Contexto da relação:
     * Um Product pode pertencer a várias Category
     * e uma Category pode conter vários Product.
     *
     * Essa relação é representada no banco por uma tabela intermediária
     * (ex.: tb_product_category) contendo duas chaves estrangeiras:
     *
     * tb_product_category
     * -------------------
     * product_id   -> FK para Product.id
     * category_id  -> FK para Category.id
     *
     * @ManyToMany(mappedBy = "categories")
     *
     * mappedBy indica que esta entidade NÃO é a dona (owner) da relação.
     * O controle da associação é feito na outra entidade (Product).
     *
     * Ou seja:
     * - A entidade Product possui a configuração principal da tabela de junção
     *   com @JoinTable.
     * - A entidade Category apenas referencia essa relação já definida.
     *
     * "categories" refere-se exatamente ao nome do atributo existente
     * na classe Product que define o relacionamento:
     *
     * private Set<Category> categories;
     *
     * Isso evita que o JPA crie duas tabelas de associação diferentes.
     *
     * Set<Product>
     * Representa todos os produtos que pertencem a esta categoria.
     *
     * Inicialização com HashSet:
     * Mantém a coleção pronta para uso e evita NullPointerException
     * ao manipular os elementos.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
