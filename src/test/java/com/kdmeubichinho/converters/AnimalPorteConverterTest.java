package com.kdmeubichinho.converters;

import com.kdmeubichinho.enums.AnimalPorte;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalPorteConverterTest {

    @Test
    void convertToDatabaseColumn() {
        AnimalPorteConverter converter = new AnimalPorteConverter();
        String convert = converter.convertToDatabaseColumn(AnimalPorte.GRANDE);
        assertEquals("Grande", convert);
    }

    @Test
    void convertToEntityAttribute() {
        AnimalPorteConverter converter = new AnimalPorteConverter();
        AnimalPorte convert = converter.convertToEntityAttribute("Grande");
        assertEquals(AnimalPorte.GRANDE, convert);
    }

    @Test
    void convertToDatabaseColumnNull() {
        AnimalPorteConverter converter = new AnimalPorteConverter();
        String convert = converter.convertToDatabaseColumn(null);
        assertNull(convert);
    }

    @Test
    void convertToEntityAttributeNull() {
        AnimalPorteConverter converter = new AnimalPorteConverter();
        AnimalPorte convert = converter.convertToEntityAttribute(null);
        assertNull(convert);
    }
}