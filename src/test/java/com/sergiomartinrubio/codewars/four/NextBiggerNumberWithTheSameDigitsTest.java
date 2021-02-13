package com.sergiomartinrubio.codewars.four;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NextBiggerNumberWithTheSameDigitsTest {
    @Test
    public void basicTests() {
        assertEquals(21, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(12));
        assertEquals(531, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(513));
        assertEquals(2071, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(2017));
        assertEquals(441, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(414));
        assertEquals(414, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(144));
        assertEquals(19009, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(10990));
    }

    @Test
    public void randomTest() {
        assertEquals(1882482274, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(1882482247));
        assertEquals(1364268833, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(1364268383));
        assertEquals(1172023931, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(1172023913));
        assertEquals(2107611233, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(2107363211));
        assertEquals(1219154436, NextBiggerNumberWithTheSameDigits.nextBiggerNumber(1219154364));
    }

}