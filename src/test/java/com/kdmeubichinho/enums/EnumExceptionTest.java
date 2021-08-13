package com.kdmeubichinho.enums;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class EnumExceptionTest {

    @Test
    void getDescricao() {
        EnumException anuncio = EnumException.ITEM_NAO_ENCONTRADO;
        assertEquals("O recurso solicitado nao esta disponivel no servidor", anuncio.getDescricao());
    }

    @Test
    void of() {
        EnumException anuncio = EnumException.of("O recurso solicitado nao esta disponivel no servidor");
        assertEquals(EnumException.ITEM_NAO_ENCONTRADO, anuncio);
    }

    @Test
    void values() {
        EnumException[] values = EnumException.values();
        assertTrue(values.length > 0);
    }

    @Test
    void valueOf() {
        EnumException exception = EnumException.valueOf("ITEM_NAO_ENCONTRADO");
        assertEquals(EnumException.ITEM_NAO_ENCONTRADO, exception);
    }
    
    @Test
    void getHttpStatus() {
        EnumException exception = EnumException.ITEM_NAO_ENCONTRADO;
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }
    
}