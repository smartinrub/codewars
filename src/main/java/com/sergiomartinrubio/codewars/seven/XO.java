package com.sergiomartinrubio.codewars.seven;

public class XO {
    public static boolean getXO(String str) {
        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char letter = Character.toLowerCase(str.charAt(i));

            if (letter == 'x') {
                xCount++;
            } else if (letter == 'o') {
                oCount++;
            }
        }

        return xCount == oCount;
    }
}
