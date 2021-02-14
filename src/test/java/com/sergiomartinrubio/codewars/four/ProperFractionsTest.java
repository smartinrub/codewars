package com.sergiomartinrubio.codewars.four;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProperFractionsTest {
    @Test
    public void Smaller_Numbers() {
        assertEquals(0, ProperFractions.properFractions(1));
        assertEquals(1, ProperFractions.properFractions(2));
        assertEquals(4, ProperFractions.properFractions(5));
        assertEquals(8, ProperFractions.properFractions(15));
        assertEquals(20, ProperFractions.properFractions(25));
    }

    @Test
    public void Big_Numbers() {
        assertEquals(6637344, ProperFractions.properFractions(9999999));
        assertEquals(500000002, ProperFractions.properFractions(500000003));
        assertEquals(608256, ProperFractions.properFractions(1532420));
        assertEquals(82260072, ProperFractions.properFractions(123456789));
        assertEquals(5890320000L, ProperFractions.properFractions(9999999999L));
    }

}