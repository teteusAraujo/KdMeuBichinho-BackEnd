package com.kdmeubichinho.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalPorteTest {

    @Test
    void getDescricao() {
        AnimalPorte animal = AnimalPorte.PEQUENO;
        assertEquals("Pequeno", animal.getDescricao());
    }

    @Test
    void of() {
        AnimalPorte animal = AnimalPorte.of("Pequeno");
        assertEquals(AnimalPorte.PEQUENO, animal);
    }

    @Test
    void values() {
        AnimalPorte[] values = AnimalPorte.values();
        assertTrue(values.length > 0);
    }

    @Test
    void valueOf() {
        AnimalPorte animal = AnimalPorte.valueOf("PEQUENO");
        assertEquals(AnimalPorte.PEQUENO, animal);
    }
}