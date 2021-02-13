package com.sergiomartinrubio.codewars.four;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindUnknownDigitTest {

    @Test
    public void testSample() {
        assertEquals("Answer for expression '1+1=?' ", 2, FindUnknownDigit.solveExpression("1+1=?"));
        assertEquals("Answer for expression '123*45?=5?088' ", 6, FindUnknownDigit.solveExpression("123*45?=5?088"));
        assertEquals("Answer for expression '-5?*-1=5?' ", 0, FindUnknownDigit.solveExpression("-5?*-1=5?"));
        assertEquals("Answer for expression '19--45=5?' ", -1, FindUnknownDigit.solveExpression("19--45=5?"));
        assertEquals("Answer for expression '??*??=302?' ", 5, FindUnknownDigit.solveExpression("??*??=302?"));
        assertEquals("Answer for expression '?*11=??' ", 2, FindUnknownDigit.solveExpression("?*11=??"));
        assertEquals("Answer for expression '??*1=??' ", 2, FindUnknownDigit.solveExpression("??*1=??"));
        assertEquals("Answer for expression '??+??=??' ", -1, FindUnknownDigit.solveExpression("??+??=??"));
        assertEquals("Answer for expression '123?45*?=?' ", 0, FindUnknownDigit.solveExpression("123?45*?=?"));
        assertEquals("Answer for expression '?*123?45=?' ", 0, FindUnknownDigit.solveExpression("?*123?45=?"));
        assertEquals("Answer for expression '-?56373--9216=-?47157' ", 8, FindUnknownDigit.solveExpression("-?56373--9216=-?47157"));
    }

}