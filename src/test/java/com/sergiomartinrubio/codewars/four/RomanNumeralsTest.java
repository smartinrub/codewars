package com.sergiomartinrubio.codewars.four;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class RomanNumeralsTest {

    @Test
    public void testToRoman() throws Exception {
        assertThat("1 converts to 'I'", RomanNumerals.toRoman(1), is("I"));
        assertThat("2 converts to 'II'", RomanNumerals.toRoman(2), is("II"));
    }

    @Test
    public void testFromRoman() throws Exception {
        assertThat("'I' converts to 1", RomanNumerals.fromRoman("I"), is(1));
        assertThat("'II' converts to 2", RomanNumerals.fromRoman("II"), is(2));
    }

    @Test
    public void testToRomanBigger() throws Exception {
        assertThat("3008 converts to 'MMMVIII'", RomanNumerals.toRoman(3008), is("MMMVIII"));
    }

    @Test
    public void testFromRomanBigger() throws Exception {
        assertThat("'IV' converts to 4", RomanNumerals.fromRoman("IV"), is(4));
        assertThat("MMCXLIV converts to '2144'", RomanNumerals.fromRoman("MMCXLIV"), is(2144));
    }

    @Test
    public void testToRomanRandom() throws Exception {
        assertThat("2898 converts to 'MMDCCCXCVIII'", RomanNumerals.toRoman(2898), is("MMDCCCXCVIII"));
    }

    @Test
    public void testToRoman2() throws Exception {
        assertThat("9 converts to 'IX'", RomanNumerals.toRoman(9), is("IX"));
    }

    @Test
    public void testFromRomanRandom() throws Exception {
        assertThat("MMDXLIII converts to '2543'", RomanNumerals.fromRoman("MMDXLIII"), is(2543));
    }
}