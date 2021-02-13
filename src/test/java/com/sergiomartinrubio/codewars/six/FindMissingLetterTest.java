package com.sergiomartinrubio.codewars.six;

import com.sergiomartinrubio.codewars.six.FindMissingLetter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindMissingLetterTest {
    @Test
    public void exampleTests() {
        assertEquals('e', FindMissingLetter.findMissingLetter(new char[]{'a', 'b', 'c', 'd', 'f'}));
        assertEquals('P', FindMissingLetter.findMissingLetter(new char[]{'O', 'Q', 'R', 'S'}));
    }

}