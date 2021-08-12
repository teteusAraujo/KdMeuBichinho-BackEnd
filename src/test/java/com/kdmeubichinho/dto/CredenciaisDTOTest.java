package com.kdmeubichinho.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredenciaisDTOTest {

    @Test
    void testAllArgsConstructorAndGetter(){
        CredenciaisDTO dto = new CredenciaisDTO("testAllArgsConstructorAndGetter", "testAllArgsConstructorAndGetter");
        assertEquals("testAllArgsConstructorAndGetter", dto.getEmail());
    }

    @Test
    void testSetters(){
        CredenciaisDTO dto = new CredenciaisDTO();
        dto.setEmail("testSetters");
        dto.setSenha("testSetterstestSetters");

        assertEquals("testSetters", dto.getEmail());
    }
}