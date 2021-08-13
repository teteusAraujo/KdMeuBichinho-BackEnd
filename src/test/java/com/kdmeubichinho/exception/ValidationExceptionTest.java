package com.kdmeubichinho.exception;

import com.kdmeubichinho.enums.EnumException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ValidationExceptionTest {

    @Test
    void getHttpStatus() {
        ValidationException validationException = new ValidationException(EnumException.ITEM_NAO_ENCONTRADO);
        assertEquals(HttpStatus.NOT_FOUND, validationException.getHttpStatus());
    }

    @Test
    void testConstructor() {
        ValidationException validationException = new ValidationException("ERRO", HttpStatus.BAD_REQUEST);
        assertEquals(HttpStatus.BAD_REQUEST, validationException.getHttpStatus());
        assertEquals("ERRO", validationException.getDescription());
    }

    @Test
    void getDescription() {
        ValidationException validationException = new ValidationException(EnumException.ITEM_NAO_ENCONTRADO);
        assertEquals("O recurso solicitado nao esta disponivel no servidor", validationException.getDescription());
    }

    @Test
    void getHttpStatusSecondConstructor() {
        ValidationException validationException = new ValidationException("TESTE Exception", HttpStatus.BAD_REQUEST);
        assertEquals(HttpStatus.BAD_REQUEST, validationException.getHttpStatus());
        assertEquals("TESTE Exception", validationException.getDescription());
    }
}