package com.sergiomartinrubio.codewars.four;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopWords {
    public static List<String> top3(String s) {
        String[] words = s
                .replaceAll(" +", " ")
                .split("[ ,./:;!_?-]");

        Map<String, Integer> countByWords = new HashMap<>();
        for (String word : words) {
            String lowerCaseWord = word.replaceAll("^'", "").toLowerCase(Locale.ROOT);
            if (Pattern.matches("[a-z]+|[a-z]+'[a-z]+|[a-z]+'", lowerCaseWord)) {
                countByWords.merge(lowerCaseWord, 1, (oldValue, newValue) -> oldValue + 1);
            }
        }

        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : countByWords.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        Map.Entry<String, Integer> secondMaxEntry = null;
        for (Map.Entry<String, Integer> entry : countByWords.entrySet()) {
            if (secondMaxEntry == null && entry.getValue().compareTo(maxEntry.getValue()) <= 0 && !entry.getKey().equals(maxEntry.getKey())
                    || secondMaxEntry != null && entry.getValue().compareTo(secondMaxEntry.getValue()) > 0
                    && !entry.getKey().equals(maxEntry.getKey())) {
                secondMaxEntry = entry;
            }
        }

        Map.Entry<String, Integer> thirdMaxEntry = null;
        for (Map.Entry<String, Integer> entry : countByWords.entrySet()) {
            if (thirdMaxEntry == null && secondMaxEntry != null && entry.getValue().compareTo(secondMaxEntry.getValue()) <= 0
                    && !entry.getKey().equals(maxEntry.getKey()) && !entry.getKey().equals(secondMaxEntry.getKey())
                    || thirdMaxEntry != null && entry.getValue().compareTo(thirdMaxEntry.getValue()) > 0
                    && !entry.getKey().equals(secondMaxEntry.getKey()) && !entry.getKey().equals(maxEntry.getKey())) {
                thirdMaxEntry = entry;
            }
        }

        return Stream.of(maxEntry != null ? maxEntry.getKey() : null,
                secondMaxEntry != null ? secondMaxEntry.getKey() : null,
                thirdMaxEntry != null ? thirdMaxEntry.getKey() : null)
                .filter(Objects::nonNull).collect(Collectors.toList());
    }
}
