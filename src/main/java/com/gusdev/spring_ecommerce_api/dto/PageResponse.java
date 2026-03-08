package com.gusdev.spring_ecommerce_api.dto;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * DTO genérico para padronizar respostas paginadas da API.
 *
 * Ao invés de retornar o objeto Page do Spring diretamente (que contém
 * informações internas desnecessárias para o cliente), encapsulamos apenas
 * os dados relevantes neste record.
 *
 * O uso de generics (<T>) permite reutilizar este record para qualquer
 * tipo de entidade (ex: PageResponse<Order>, PageResponse<User>).
 */
public record PageResponse<T>(

        // Lista com os itens da página atual
        List<T> content,

        // Índice da página atual (começa em 0)
        int page,

        // Quantidade máxima de itens por página
        int size,

        // Total de itens em todas as páginas combinadas
        long totalElements,

        // Total de páginas disponíveis
        int totalPages,

        // Indica se esta é a primeira página
        boolean first,

        // Indica se esta é a última página
        boolean last,

        // Indica se a página não contém nenhum item
        boolean empty

) {
    /**
     * Factory method que converte o objeto Page do Spring em um PageResponse.
     *
     * O uso de um método estático "from" é um padrão comum para conversão
     * de objetos, mantendo a lógica de mapeamento centralizada no próprio DTO.
     *
     * @param page objeto Page retornado pelo repositório Spring Data
     * @return novo PageResponse com os dados extraídos do Page
     */
    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),       // itens da página atual
                page.getNumber(),        // número da página (base 0)
                page.getSize(),          // tamanho da página
                page.getTotalElements(), // total de registros no banco
                page.getTotalPages(),    // total de páginas
                page.isFirst(),          // é a primeira página?
                page.isLast(),           // é a última página?
                page.isEmpty()           // a página está vazia?
        );
    }
}