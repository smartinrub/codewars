package com.sergiomartinrubio.codewars.six;

public class FindMissingLetter {
    public static char findMissingLetter(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if ((int) array[i + 1] - (int) array[i] > 1) {
                return Character.toChars(array[i] + 1)[0];
            }
        }
        return ' ';
    }
}

