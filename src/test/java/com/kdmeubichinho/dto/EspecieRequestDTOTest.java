package com.kdmeubichinho.dto;

import com.kdmeubichinho.entities.Especie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EspecieRequestDTOTest {

    @Test
    void testAllArgsConstructorAndGetter(){
        EspecieRequestDTO dto = new EspecieRequestDTO("testAllArgsConstructorAndGetter");
        assertEquals("testAllArgsConstructorAndGetter", dto.getNome());
    }

    @Test
    void testSetter(){
        EspecieRequestDTO dto = new EspecieRequestDTO();
        dto.setNome("testSetter");

        assertEquals("testSetter", dto.getNome());
    }

    @Test
    void testBuild(){
        EspecieRequestDTO dto = new EspecieRequestDTO("testBuild");
        Especie build = dto.build();
        assertEquals("testBuild", build.getNome());
    }
}