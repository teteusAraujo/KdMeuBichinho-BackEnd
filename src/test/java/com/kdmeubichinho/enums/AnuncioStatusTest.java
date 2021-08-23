package com.kdmeubichinho.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnuncioStatusTest {

    @Test
    void getDescricao() {
        AnuncioStatus anuncio = AnuncioStatus.ATIVO;
        assertEquals("Ativo", anuncio.getDescricao());
    }

    @Test
    void of() {
        AnuncioStatus anuncio = AnuncioStatus.of("Ativo");
        assertEquals(AnuncioStatus.ATIVO, anuncio);
    }

    @Test
    void values() {
        AnuncioStatus[] values = AnuncioStatus.values();
        assertTrue(values.length > 0);
    }

    @Test
    void valueOf() {
        AnuncioStatus anuncio = AnuncioStatus.valueOf("ATIVO");
        assertEquals(AnuncioStatus.ATIVO, anuncio);
    }
}