package com.sergiomartinrubio.codewars.four;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StripCommentsTest {
    @Test
    public void stripComments() throws Exception {
        assertEquals(
                "apples, pears\ngrapes\nbananas",
                StripComments.stripComments("apples, pears # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"})
        );

        assertEquals(
                "a\nc\nd",
                StripComments.stripComments("a #b\nc\nd $e f g", new String[]{"#", "$"})
        );

    }

    @Test
    public void stripComments2() throws Exception {
        assertEquals(
                "a\n" +
                        " b\n" +
                        "c",
                StripComments.stripComments("a \n" +
                        " b \n" +
                        "c ", new String[]{"#", "$"})
        );

    }

}