package com.gusdev.spring_ecommerce_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;

    @Column(name = "img_url")
    private String imgUrl;

    /*
     * Associação Many-to-Many entre Product e Category.
     *
     * Conceito:
     * Um Product pode pertencer a várias Category e uma Category pode conter vários Product.
     * Em bancos relacionais esse tipo de relação é implementado através de uma tabela intermediária.
     *
     * @ManyToMany
     * Indica para o JPA/Hibernate que existe uma relação muitos-para-muitos entre as entidades.
     *
     * @JoinTable
     * Define explicitamente a tabela de junção responsável por armazenar os vínculos
     * entre Product e Category no banco de dados.
     *
     * name = "tb_product_category"
     * Nome da tabela intermediária que armazenará os relacionamentos.
     *
     * joinColumns = @JoinColumn(name = "product_id")
     * Define a coluna da tabela intermediária que referencia a chave primária
     * da entidade atual (Product).
     *
     * inverseJoinColumns = @JoinColumn(name = "category_id")
     * Define a coluna da tabela intermediária que referencia a chave primária
     * da entidade associada (Category).
     *
     * Estrutura gerada no banco:
     *
     * tb_product_category
     * -------------------
     * product_id   (FK -> Product.id)
     * category_id  (FK -> Category.id)
     *
     * Set<Category>
     * Utilizamos Set para evitar duplicidade de categorias associadas ao mesmo produto.
     *
     * Inicialização com HashSet
     * Garante que a coleção nunca seja nula, evitando NullPointerException
     * ao adicionar ou manipular categorias.
     */
    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @JsonIgnore
    public Set<Order> getOrders() {
        Set<Order> set = new HashSet<>();
        for (OrderItem item : items) {
            set.add(item.getOrder());
        }
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
