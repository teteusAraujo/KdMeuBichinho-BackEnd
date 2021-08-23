package com.kdmeubichinho.converters;

import com.kdmeubichinho.enums.AnimalClassificacaoEtaria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalClassificacaoEtariaConverterTest {

    @Test
    void convertToDatabaseColumn() {
        AnimalClassificacaoEtariaConverter animalConverter = new AnimalClassificacaoEtariaConverter();
        String s = animalConverter.convertToDatabaseColumn(AnimalClassificacaoEtaria.ADULTO);
        assertEquals("Adulto", s);
    }

    @Test
    void convertToEntityAttribute() {
        AnimalClassificacaoEtariaConverter animalConverter = new AnimalClassificacaoEtariaConverter();
        AnimalClassificacaoEtaria convert = animalConverter.convertToEntityAttribute("Adulto");
        assertEquals(AnimalClassificacaoEtaria.ADULTO, convert);
    }

    @Test
    void convertToDatabaseColumnNull() {
        AnimalClassificacaoEtariaConverter animalConverter = new AnimalClassificacaoEtariaConverter();
        String s = animalConverter.convertToDatabaseColumn(null);
        assertNull(s);
    }

    @Test
    void convertToEntityAttributeNull() {
        AnimalClassificacaoEtariaConverter animalConverter = new AnimalClassificacaoEtariaConverter();
        AnimalClassificacaoEtaria convert = animalConverter.convertToEntityAttribute(null);
        assertNull(convert);
    }
}