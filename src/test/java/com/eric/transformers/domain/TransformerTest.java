package com.eric.transformers.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformerTest {

    @Test
    public void testRating() {
        Transformer transformer = Transformer.builder()
                .strength(0)
                .intelligence(0)
                .speed(0)
                .endurance(0)
                .firepower(0)
                .build();

        assertEquals(0, transformer.getRating());

        transformer.setStrength(10);
        assertEquals(10, transformer.getRating());

        transformer.setCourage(10);
        transformer.setRank(10);
        assertEquals(10, transformer.getRating());

        transformer.setFirepower(5);
        assertEquals(15, transformer.getRating());

        transformer.setIntelligence(2);
        assertEquals(17, transformer.getRating());

        transformer.setSpeed(4);
        assertEquals(21, transformer.getRating());

        transformer.setEndurance(8);
        assertEquals(29, transformer.getRating());
    }


}