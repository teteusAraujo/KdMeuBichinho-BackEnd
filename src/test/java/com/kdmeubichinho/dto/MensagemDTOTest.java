package com.kdmeubichinho.dto;

import com.kdmeubichinho.entities.Anuncio;
import com.kdmeubichinho.entities.Mensagem;
import com.kdmeubichinho.entities.Pessoa;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MensagemDTOTest {

    @Test
    void testAllArgsConstructor(){
        MensagemDTO mensagem = new MensagemDTO(1, new Date(), "mensagem", new Pessoa(), new Anuncio());
        assertNotNull(mensagem);
    }

    @Test
    void testNoArgsConstructor(){
        MensagemDTO mensagemDTO = new MensagemDTO();
        assertNotNull(mensagemDTO);
    }

    @Test
    void testBuid(){
        MensagemDTO mensagemDTO = new MensagemDTO();
        Mensagem build = mensagemDTO.build();
        assertNotNull(build);
        assertTrue(build instanceof Mensagem);
    }
}