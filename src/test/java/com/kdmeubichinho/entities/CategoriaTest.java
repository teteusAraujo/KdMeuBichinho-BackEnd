package com.kdmeubichinho.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    @Test
    void allArgsConstructorAndGetter(){
        Categoria categoria = new Categoria(1, "classificacao-categoria");
        assertEquals(1, categoria.getIdCategoria());
        assertEquals(1, categoria.getId());
        assertEquals("classificacao-categoria", categoria.getClassificacao());
    }

    @Test
    void testSetter(){
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(3);
        categoria.setClassificacao("classificacao-categoria-2");

        assertEquals(3, categoria.getId());
        assertEquals("classificacao-categoria-2", categoria.getClassificacao());
    }
}