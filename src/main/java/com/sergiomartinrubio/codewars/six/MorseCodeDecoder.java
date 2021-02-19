package com.sergiomartinrubio.codewars.six;

public class MorseCodeDecoder {
    public static String decode(String morseCode) {
        String[] morseWords = morseCode.trim().split(" {3}");

        StringBuilder result = new StringBuilder();
        for (String morseWord : morseWords) {
            String[] morseLetters = morseWord.split(" ");
            for (String morseLetter : morseLetters) {
                result.append(MorseCode.get(morseLetter.trim()));
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
}
