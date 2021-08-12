package com.kdmeubichinho.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTipoTest {

    @Test
    void getDescricao() {
        AnimalTipo animal = AnimalTipo.CACHORRO;
        assertEquals("Cachorro", animal.getDescricao());
    }

    @Test
    void of() {
        AnimalTipo animal = AnimalTipo.of("Cachorro");
        assertEquals(AnimalTipo.CACHORRO, animal);
    }

    @Test
    void values() {
        AnimalTipo[] values = AnimalTipo.values();
        assertTrue(values.length > 0);
    }

    @Test
    void valueOf() {
        AnimalTipo animal = AnimalTipo.valueOf("CACHORRO");
        assertEquals(AnimalTipo.CACHORRO, animal);
    }
}