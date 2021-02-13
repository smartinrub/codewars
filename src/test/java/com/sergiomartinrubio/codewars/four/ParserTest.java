package com.sergiomartinrubio.codewars.four;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void fixedTests() {
        assertEquals(1 , Parser.parseInt("one"));
        assertEquals(20 , Parser.parseInt("twenty"));
        assertEquals(246 , Parser.parseInt("two hundred forty-six"));
        assertEquals(26754 , Parser.parseInt("twenty-six thousand seven hundred and fifty-four"));
        assertEquals(101 , Parser.parseInt("one hundred one"));
        assertEquals(100 , Parser.parseInt("one hundred"));
        assertEquals(35000 , Parser.parseInt("thirty-five thousand"));
        assertEquals(666666 , Parser.parseInt("six hundred sixty-six thousand six hundred sixty-six"));
        assertEquals(1000000 , Parser.parseInt("one million"));
    }

}