package com.kdmeubichinho.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FotoTest {

    @Test
    void allArgsConstructorAndGetter(){
        Foto foto = new Foto(1, "caminho-imagem");
        assertEquals(1, foto.getIdFoto());
        assertEquals("caminho-imagem", foto.getCaminho());
    }

    @Test
    void testSetter(){
        Foto foto = new Foto();
        foto.setIdFoto(3);
        foto.setCaminho("caminho-2");

        assertEquals(3, foto.getIdFoto());
        assertEquals("caminho-2", foto.getCaminho());
    }
}