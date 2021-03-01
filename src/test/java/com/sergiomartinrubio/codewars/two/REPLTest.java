package com.sergiomartinrubio.codewars.two;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class REPLTest {
    @Test
    public void basicTests() {
        REPL interpreter = new REPL();

        // Basic arithmetic
//        assertEquals(2, interpreter.input("1 + 1"), 0.0);
//        assertEquals(1, interpreter.input("2 - 1"), 0.0);
//        assertEquals(6, interpreter.input("2 * 3"), 0.0);
//        assertEquals(2, interpreter.input("8 / 4"), 0.0);
//        assertEquals(3, interpreter.input("7 % 4"), 0.0);


        // Variables
        assertEquals(1, interpreter.input("x = 1"), 0.0);
        assertEquals(1, interpreter.input("x"), 0.0);
        assertEquals(4, interpreter.input("x + 3"), 0.0);
        assertFail("input: 'y'", () -> interpreter.input("y"));
    }

    @Test
    public void basicTests2() {
        REPL interpreter = new REPL();

        // Basic arithmetic
//        assertEquals(10, interpreter.input("4 + 2 * 3"), 0.0);
//        assertEquals(6, interpreter.input("4 / 2 * 3"), 0.0);
//        assertEquals(8, interpreter.input("7 % 2 * 8"), 0.0);
//        assertEquals(18, interpreter.input("(4 + 2) * 3"), 0.0);
//        assertEquals(6, interpreter.input("(8 - (4 + 2)) * 3"), 0.0);
//        assertEquals(2, interpreter.input("(7 + 3) / (2 * 2 + 1)"), 0.0);
        assertEquals(2, interpreter.input("1 2"), 0.0);
//        assertEquals(2, interpreter.input("9"), 0.0);
    }


    @Test
    public void basicTests3() {
        REPL interpreter = new REPL();

        // Variables
        assertEquals(7, interpreter.input("x = 7"), 0.0);
        assertEquals(7, interpreter.input("x"), 0.0);
        assertEquals(10, interpreter.input("x + 3"), 0.0);
        assertEquals(0, interpreter.input("y"), 0.0);
        assertEquals(12, interpreter.input("y = x + 5"), 0.0);
    }

    private static void assertFail(String msg, Runnable runnable) {
        try {
            runnable.run();
            fail(msg);
        } catch (Exception e) {
            // Ok
        }
    }
}