package com.sergiomartinrubio.codewars.four;


import java.util.Map;

//        | Symbol | Value |
//        |----------------|
//        | I      |  1    |
//        | V      |  5    |
//        | X      |  10   |
//        | L      |  50   |
//        | C      |  100  |
//        | D      |  500  |
//        | M      |  1000 |
public class RomanNumerals {
    public static final Map<Character, Integer> NUMBERS_BY_ROMAN_NUMBER = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    public static final Map<Integer, Character> ROMAN_NUMBERS_BY_NUMBER = Map.of(
            1, 'I',
            5, 'V',
            10, 'X',
            50, 'L',
            100, 'C',
            500, 'D',
            1000, 'M'
    );

    public static String toRoman(int n) {
        StringBuilder romanNumber = new StringBuilder();
        while (n > 0) {
            if (n >= 1000) {
                romanNumber.append(ROMAN_NUMBERS_BY_NUMBER.get(1000));
                n -= 1000;
            } else if (n >= 900) {
                romanNumber
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(100))
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(1000));
                n -= 900;
            } else if (n >= 500) {
                romanNumber.append(ROMAN_NUMBERS_BY_NUMBER.get(500));
                n -= 500;
            } else if (n >= 400) {
                romanNumber
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(100))
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(500));
                n -= 400;
            } else if (n >= 100) {
                romanNumber.append(ROMAN_NUMBERS_BY_NUMBER.get(100));
                n -= 100;
            } else if (n >= 90) {
                romanNumber
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(10))
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(100));
                n -= 90;
            } else if (n >= 50) {
                romanNumber.append(ROMAN_NUMBERS_BY_NUMBER.get(50));
                n -= 50;
            } else if (n >= 40) {
                romanNumber
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(10))
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(50));
                n -= 40;
            } else if (n >= 10) {
                romanNumber.append(ROMAN_NUMBERS_BY_NUMBER.get(10));
                n -= 10;
            } else if (n >= 9) {
                romanNumber
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(1))
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(10));
                n -= 9;
            } else if (n >= 5) {
                romanNumber.append(ROMAN_NUMBERS_BY_NUMBER.get(5));
                n -= 5;
            } else if (n >= 4) {
                romanNumber
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(1))
                        .append(ROMAN_NUMBERS_BY_NUMBER.get(5));
                n -= 4;
            } else if (n >= 1) {
                romanNumber.append(ROMAN_NUMBERS_BY_NUMBER.get(1));
                n -= 1;
            }
        }
        return romanNumber.toString();
    }

    public static int fromRoman(String romanNumeral) {
        char[] chars = romanNumeral.toCharArray();
        int number = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i < chars.length  - 1 && (NUMBERS_BY_ROMAN_NUMBER.get(chars[i + 1]) > NUMBERS_BY_ROMAN_NUMBER.get(chars[i]))) {
                number += NUMBERS_BY_ROMAN_NUMBER.get(chars[i + 1]) - NUMBERS_BY_ROMAN_NUMBER.get(chars[i]);
                i++;
            } else {
                number += NUMBERS_BY_ROMAN_NUMBER.get(chars[i]);
            }
        }
        return number;
    }
}
