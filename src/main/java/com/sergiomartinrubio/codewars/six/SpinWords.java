package com.sergiomartinrubio.codewars.six;

public class SpinWords {
    public String spinWords(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder reversed = new StringBuilder();

        if (words[0].length() >= 5) {
            reversed.append(new StringBuilder(words[0]).reverse());
        } else {
            reversed.append(words[0]);
        }

        for (int i = 1; i < words.length; i++) {
            if (words[i].length() >= 5) {
                StringBuilder reversedWord = new StringBuilder(words[i]).reverse();
                reversed.append(" ").append(reversedWord);
            } else {
                reversed.append(" ").append(words[i]);
            }
        }

        return reversed.toString();
    }
}
