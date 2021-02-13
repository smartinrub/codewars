package com.sergiomartinrubio.codewars.four;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StripComments {
    public static String stripComments(String text, String[] commentSymbols) {
        String[] lines = text.split("\n");

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            List<String> scapeSymbols = Arrays.stream(commentSymbols).map(Pattern::quote).collect(Collectors.toList());
            Pattern pattern = Pattern.compile(String.join("|", scapeSymbols));
            Matcher matcher = pattern.matcher(lines[i]);
            if (matcher.find()) {
                String lineWithoutComment = lines[i].substring(0, matcher.start()).replaceAll("\\s+$", "");
                result.append(lineWithoutComment);
            } else {
                result.append(lines[i].replaceAll("\\s+$", ""));
            }

            if (i != lines.length - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}
