package com.kdmeubichinho.entities;

import com.kdmeubichinho.enums.AnuncioStatus;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnuncioTest {

    @Test
    void testAllArgsConstructorAndGetter() {
        Date d = new Date();
        Pessoa p = new Pessoa();
        Animal a = new Animal();
        Categoria c = new Categoria();
        Set set = new TreeSet();
        Anuncio anuncio = new Anuncio(1, AnuncioStatus.ATIVO, d, d, p, a, c, set);

        assertEquals(1, anuncio.getIdAnuncio());
        assertEquals(AnuncioStatus.ATIVO, anuncio.getStatus());
        assertEquals(d, anuncio.getDataCriacao());
        assertEquals(d, anuncio.getDataEncerramento());
        assertEquals(p, anuncio.getIdPessoa());
        assertEquals(a, anuncio.getIdAnimal());
        assertEquals(c, anuncio.getIdCategoria());
        assertTrue(anuncio.getMensagens().isEmpty());
    }

    @Test
    void testMinimalConstructor(){
        Anuncio anuncio = new Anuncio(AnuncioStatus.ATIVO, new Date());
        assertEquals(AnuncioStatus.ATIVO, anuncio.getStatus());
    }

    @Test
    void testSetter(){
        Anuncio a = new Anuncio();
        a.setStatus(AnuncioStatus.ATIVO);
        a.setIdAnimal(new Animal());
        a.setIdPessoa(new Pessoa());
        a.setIdAnuncio(3);
        a.setDataEncerramento(new Date());
        a.setDataCriacao(new Date());
        a.setIdCategoria(new Categoria());
        a.setMensagens(new TreeSet<>());

        assertEquals(3, a.getIdAnuncio());
    }
}