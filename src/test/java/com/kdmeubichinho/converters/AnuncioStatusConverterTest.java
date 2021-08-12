package com.kdmeubichinho.converters;

import com.kdmeubichinho.enums.AnuncioStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnuncioStatusConverterTest {

    @Test
    void convertToDatabaseColumn() {
        AnuncioStatusConverter converter = new AnuncioStatusConverter();
        String convert = converter.convertToDatabaseColumn(AnuncioStatus.ATIVO);
        assertEquals("Ativo", convert);
    }

    @Test
    void convertToEntityAttribute() {
        AnuncioStatusConverter converter = new AnuncioStatusConverter();
        AnuncioStatus convert = converter.convertToEntityAttribute("Inativo");
        assertEquals(AnuncioStatus.INATIVO, convert);
    }

    @Test
    void convertToDatabaseColumnNull() {
        AnuncioStatusConverter converter = new AnuncioStatusConverter();
        String convert = converter.convertToDatabaseColumn(null);
        assertNull(convert);
    }

    @Test
    void convertToEntityAttributeNull() {
        AnuncioStatusConverter converter = new AnuncioStatusConverter();
        AnuncioStatus convert = converter.convertToEntityAttribute(null);
        assertNull(convert);
    }
}