package com.sergiomartinrubio.codewars.six;

import java.util.*;

public class DuplicateEncoder {
    static String encode(String word){
        String lowerCaseWord = word.toLowerCase(Locale.ROOT);
        Map<Character, List<Integer>> countByLetters = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lowerCaseWord.length(); i++) {
            char letter = lowerCaseWord.charAt(i);
            List<Integer> positions = countByLetters.get(letter);
            if (positions == null) {
                List<Integer> newPositions = new ArrayList<>();
                newPositions.add(i);
                countByLetters.put(letter, newPositions);
                result.append('(');
            } else {
                result.append(')');
                positions.forEach(position -> result.setCharAt(position, ')'));
            }
        }

        return result.toString();
    }

}
