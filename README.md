<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
</head>
<body>

<h1>🛒 Spring E-commerce API</h1>

<p>
API REST de e-commerce desenvolvida com <strong>Java 25 + Spring Boot 4</strong>, 
seguindo arquitetura em camadas, boas práticas REST e modelagem de domínio robusta.
</p>

<p>
Projeto baseado no curso de <strong>Spring Boot + JPA/Hibernate</strong>, com melhorias e extensões.
</p>

<hr>

<h2>🚀 Tecnologias</h2>

<ul>
  <li>Java 25</li>
  <li>Spring Boot 4.0.3</li>
  <li>Spring Web</li>
  <li>Spring Data JPA</li>
  <li>Hibernate</li>
  <li>H2 Database (ambiente de teste)</li>
  <li>Maven</li>
  <li>Postman (testes e documentação)</li>
  <li>Swagger / OpenAPI (documentação interativa)</li>
</ul>

<hr>

<h2>📐 Arquitetura</h2>

<p>O projeto segue o padrão de arquitetura em camadas:</p>

<ul>
  <li><strong>Resource</strong> → Controllers REST</li>
  <li><strong>Service</strong> → Regras de negócio</li>
  <li><strong>Repository</strong> → Persistência de dados</li>
</ul>

<hr>

<h2>📦 Modelo de Domínio</h2>

<ul>
  <li>User</li>
  <li>Order</li>
  <li>OrderItem</li>
  <li>Product</li>
  <li>Category</li>
  <li>Payment</li>
</ul>

<h3>🔗 Relacionamentos</h3>

<ul>
  <li>User → OneToMany → Order</li>
  <li>Order → OneToMany → OrderItem</li>
  <li>Product ↔ ManyToMany ↔ Category</li>
  <li>OrderItem → ManyToOne → Product</li>
  <li>Order → OneToOne → Payment</li>
</ul>

<hr>

<h2>⚙️ Funcionalidades</h2>

<ul>
  <li>CRUD completo de usuários</li>
  <li>Gerenciamento de pedidos</li>
  <li>Relacionamento entre produtos e categorias</li>
  <li>Cálculo de subtotal e total de pedidos</li>
  <li>Seed de banco para testes</li>
  <li>Tratamento global de exceções</li>
</ul>

<hr>

<h2>📡 Documentação da API</h2>

<h3>📘 Postman</h3>

<p>
A documentação oficial da API pode ser acessada aqui:
</p>

<ul>
  <li>
    👉 <a href="https://documenter.getpostman.com/view/38028614/2sBXijJWpL" target="_blank">
    Documentação completa (Postman)</a>
  </li>
  <li>
    👉 <a href="https://www.postman.com/gusta-team/spring-e-commerce-api/documentation/38028614-3bdb628e-202b-488a-ad01-c3196b0000c9" target="_blank">
    Collection do Postman</a>
  </li>
</ul>

<p>
O Postman permite organizar e documentar endpoints, incluindo parâmetros, headers e exemplos de requisição/resposta.
</p>

<hr>

<h2>📄 Swagger / OpenAPI</h2>

<p>
A API pode ser documentada automaticamente utilizando Swagger (OpenAPI), que gera uma interface interativa para testar endpoints diretamente no navegador.
</p>

<h3>📦 Dependência</h3>

<pre>
&lt;dependency&gt;
  &lt;groupId&gt;org.springdoc&lt;/groupId&gt;
  &lt;artifactId&gt;springdoc-openapi-starter-webmvc-ui&lt;/artifactId&gt;
  &lt;version&gt;2.5.0&lt;/version&gt;
&lt;/dependency&gt;
</pre>

<h3>▶️ Acesso</h3>

<pre>
http://localhost:8080/swagger-ui.html
</pre>

<h3>💡 Exemplo</h3>

<pre>
@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Gerenciamento de usuários")
public class UserResource {

    @GetMapping
    @Operation(summary = "Listar usuários")
    public List&lt;User&gt; findAll() {
        return service.findAll();
    }
}
</pre>

<p>
O Swagger gera documentação automática baseada nas anotações do código e segue o padrão OpenAPI.
</p>

<hr>

<h2>🧪 Banco de Dados (H2)</h2>

<ul>
  <li><strong>URL:</strong> http://localhost:8080/h2-console</li>
  <li><strong>JDBC:</strong> jdbc:h2:mem:testdb</li>
  <li><strong>User:</strong> sa</li>
  <li><strong>Password:</strong> (vazio)</li>
</ul>

<hr>

<h2>📥 Exemplo de Requisição</h2>

<pre>
POST /users

{
  "name": "Bob Brown",
  "email": "bob@gmail.com",
  "phone": "977557755",
  "password": "123456"
}
</pre>

<hr>

<h2>⚠️ Tratamento de Exceções</h2>

<ul>
  <li>ResourceNotFoundException → 404</li>
  <li>DatabaseException → 400/500</li>
  <li>StandardError → resposta padronizada</li>
</ul>

<hr>

<h2>▶️ Como executar</h2>

<ol>
  <li>Clonar o repositório</li>
  <li>Importar como projeto Maven</li>
  <li>Executar a aplicação</li>
</ol>

<pre>
git clone https://github.com/GusLM/spring-ecommerce-api.git
</pre>

<hr>

<h2>📌 Objetivo</h2>

<p>Projeto desenvolvido para prática de:</p>

<ul>
  <li>APIs REST com Spring Boot</li>
  <li>JPA / Hibernate</li>
  <li>Arquitetura em camadas</li>
  <li>Modelagem de domínio</li>
  <li>Boas práticas de backend</li>
</ul>

<hr>

<h2>👨‍💻 Autor</h2>

<p>
Gustavo Santos
</p>

</body>
</html>
