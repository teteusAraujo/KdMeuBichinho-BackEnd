package com.kdmeubichinho.converters;

import com.kdmeubichinho.enums.AnimalTipo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTipoConverterTest {

    @Test
    void convertToDatabaseColumn() {
        AnimalTipoConverter converter = new AnimalTipoConverter();
        String convert = converter.convertToDatabaseColumn(AnimalTipo.CACHORRO);
        assertEquals("Cachorro", convert);
    }

    @Test
    void convertToEntityAttribute() {
        AnimalTipoConverter converter = new AnimalTipoConverter();
        AnimalTipo convert = converter.convertToEntityAttribute("Cachorro");
        assertEquals(AnimalTipo.CACHORRO, convert);
    }

    @Test
    void convertToDatabaseColumnNull() {
        AnimalTipoConverter converter = new AnimalTipoConverter();
        String convert = converter.convertToDatabaseColumn(null);
        assertNull(convert);
    }

    @Test
    void convertToEntityAttributeNull() {
        AnimalTipoConverter converter = new AnimalTipoConverter();
        AnimalTipo convert = converter.convertToEntityAttribute(null);
        assertNull(convert);
    }
}