package com.kdmeubichinho.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EspecieTest {

    @Test
    void allArgsConstructorAndGetter(){
        Especie especie = new Especie(1, "nome-especie");
        assertEquals(1, especie.getIdEspecie());
        assertEquals(1, especie.getId());
        assertEquals("nome-especie", especie.getNome());
    }

    @Test
    void testSetter(){
        Especie especie = new Especie();
        especie.setIdEspecie(3);
        especie.setNome("nome-especie-2");

        assertEquals(3, especie.getId());
        assertEquals("nome-especie-2", especie.getNome());
    }
}