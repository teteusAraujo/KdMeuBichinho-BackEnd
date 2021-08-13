package com.kdmeubichinho.exception;

import com.kdmeubichinho.dto.CustomExceptionDTO;
import com.kdmeubichinho.enums.EnumException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;

import static org.junit.jupiter.api.Assertions.*;

class GlobalControllerExceptionHandlerTest {

    @Test
    void handleCustomSimpleException() {
        GlobalControllerExceptionHandler handler = new GlobalControllerExceptionHandler();
        ValidationException exception = new ValidationException(EnumException.ITEM_NAO_ENCONTRADO);
        ResponseEntity<CustomExceptionDTO> responseEntity = handler.handleCustomSimpleException(exception);

        assertEquals("O recurso solicitado nao esta disponivel no servidor", ((CustomExceptionDTO)responseEntity.getBody()).getMessage());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void handleJpaException() {
        GlobalControllerExceptionHandler handler = new GlobalControllerExceptionHandler();
        JpaSystemException exception = new JpaSystemException(new RuntimeException("#Exception JPA#"));
        ResponseEntity<CustomExceptionDTO> responseEntity = handler.handleJpaException(exception);

        assertTrue(responseEntity.getBody().getMessage().contains("#Exception JPA#"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void handleJpaExceptionAnotherConstructor() {
        GlobalControllerExceptionHandler handler = new GlobalControllerExceptionHandler();
        JpaSystemException exception = new JpaSystemException(new RuntimeException("#Exception JPA#Exception JPA", new RuntimeException("#Exception JPA#Exception JPA")));
        ResponseEntity<CustomExceptionDTO> responseEntity = handler.handleJpaException(exception);

        assertTrue(responseEntity.getBody().getMessage().contains("Exception JPA"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void handleAllAnotherException() {
        GlobalControllerExceptionHandler handler = new GlobalControllerExceptionHandler();
        NumberFormatException exception = new NumberFormatException("NFE");
        ResponseEntity<CustomExceptionDTO> responseEntity = handler.handleAllOtherException(exception);

        assertEquals("NFE", ((CustomExceptionDTO)responseEntity.getBody()).getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}