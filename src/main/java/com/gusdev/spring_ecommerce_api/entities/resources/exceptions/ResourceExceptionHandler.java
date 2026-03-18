package com.gusdev.spring_ecommerce_api.entities.resources.exceptions;

import com.gusdev.spring_ecommerce_api.services.exceptions.DatabaseException;
import com.gusdev.spring_ecommerce_api.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

/**
 * Classe responsável por interceptar e tratar exceções lançadas em toda a aplicação.
 *
 * A anotação @ControllerAdvice transforma esta classe em um "interceptador global",
 * ou seja, ela monitora todos os Controllers e captura exceções específicas
 * antes que elas cheguem ao cliente como um erro genérico (500 Internal Server Error).
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    /**
     * Método que trata especificamente a exceção ResourceNotFoundException.
     *
     * A anotação @ExceptionHandler(ResourceNotFoundException.class) instrui o Spring
     * a chamar este método automaticamente sempre que uma ResourceNotFoundException
     * for lançada em qualquer parte da aplicação.
     *
     * @param e       A exceção capturada, que contém a mensagem de erro detalhada.
     * @param request O objeto da requisição HTTP, usado para extrair informações
     *                como a URL que originou o erro.
     * @return Um ResponseEntity contendo o corpo do erro padronizado e o status HTTP 404.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

        // Mensagem amigável que descreve o tipo de erro ocorrido
        String error = "Resource not found";

        // Define o status HTTP 404 (NOT_FOUND), que indica que o recurso solicitado não existe
        HttpStatus status = HttpStatus.NOT_FOUND;

        // Monta o objeto de erro padronizado com todas as informações relevantes:
        // - Instant.now()        → data/hora exata em que o erro ocorreu
        // - status.value()       → código numérico do status HTTP (ex: 404)
        // - error                → descrição curta do erro
        // - e.getMessage()       → mensagem detalhada vinda da exceção (ex: "User id 5 not found")
        // - request.getRequestURI() → caminho da URL que causou o erro (ex: /users/5)
        StandardError standardError =
                new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        // Retorna a resposta HTTP com o status 404 e o corpo contendo o erro padronizado
        return ResponseEntity.status(status).body(standardError);
    }

    /**
     * Método que trata especificamente a exceção DatabaseException.
     *
     * A anotação @ExceptionHandler(DatabaseException.class) instrui o Spring
     * a chamar este método automaticamente sempre que uma DatabaseException
     * for lançada em qualquer parte da aplicação.
     *
     * @param e       A exceção capturada, que contém a mensagem de erro detalhada.
     * @param request O objeto da requisição HTTP, usado para extrair informações
     *                como a URL que originou o erro.
     * @return Um ResponseEntity contendo o corpo do erro padronizado e o status HTTP 400.
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {

        // Mensagem amigável que descreve o tipo de erro ocorrido
        String error = "Database error";

        // Define o status HTTP 400 (BAD_REQUEST), que indica que o recurso solicitado não existe
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Monta o objeto de erro padronizado com todas as informações relevantes:
        // - Instant.now()        → data/hora exata em que o erro ocorreu
        // - status.value()       → código numérico do status HTTP (ex: 400)
        // - error                → descrição curta do erro
        // - e.getMessage()       → mensagem detalhada vinda da exceção
        // - request.getRequestURI() → caminho da URL que causou o erro (ex: /users/5)
        StandardError standardError =
                new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        // Retorna a resposta HTTP com o status 400 e o corpo contendo o erro padronizado
        return ResponseEntity.status(status).body(standardError);
    }
}