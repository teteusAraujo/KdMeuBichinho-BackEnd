package com.kdmeubichinho.entities;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MensagemTest {

    @Test
    void noArgsConstructor(){
        Mensagem mensagem = new Mensagem();
        assertNotNull(mensagem);
    }

    @Test
    void allArgsConstructorAndGetter(){
        Date d = new Date();
        Pessoa p = new Pessoa();
        Anuncio a = new Anuncio();
        Mensagem mensagem = new Mensagem(1, d, "mensagem", p, a);
        assertEquals(1, mensagem.getIdMensagem());
        assertEquals(1, mensagem.getId());
        assertEquals(d, mensagem.getDataMensagem());
        assertEquals("mensagem", mensagem.getMsgConteudo());
        assertEquals(p, mensagem.getIdPessoa());
        assertEquals(a, mensagem.getIdAnuncio());
    }

    @Test
    void setters(){
        Mensagem m = new Mensagem();
        m.setIdMensagem(1);
        m.setDataMensagem(new Date());
        m.setMsgConteudo("msg-de-teste");
        m.setIdPessoa(new Pessoa());
        m.setIdAnuncio(new Anuncio());

        assertEquals("msg-de-teste", m.getMsgConteudo());
    }
}