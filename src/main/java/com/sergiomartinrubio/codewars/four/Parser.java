package com.sergiomartinrubio.codewars.four;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class Parser {
    private static final Map<String, Integer> INTEGERS_BY_STRING = ofEntries(
            entry("zero", 0),
            entry("one", 1),
            entry("two", 2),
            entry("three", 3),
            entry("four", 4),
            entry("five", 5),
            entry("six", 6),
            entry("seven", 7),
            entry("eight", 8),
            entry("nine", 9),
            entry("ten", 10),
            entry("eleven", 11),
            entry("twelve", 12),
            entry("thirteen", 13),
            entry("fourteen", 14),
            entry("fifteen", 15),
            entry("sixteen", 16),
            entry("seventeen", 17),
            entry("eighteen", 18),
            entry("nineteen", 19),
            entry("twenty", 20),
            entry("thirty", 30),
            entry("forty", 40),
            entry("fifty", 50),
            entry("sixty", 60),
            entry("seventy", 70),
            entry("eighty", 80),
            entry("ninety", 90),
            entry("hundred", 100),
            entry("thousand", 1000),
            entry("million", 1000000)
    );


    public static int parseInt(String numStr) {
        String numStrWithoutAnd = numStr.replaceAll(" and ", " ");

        List<String> numbersWithSpaces = Arrays.stream(numStrWithoutAnd.split(" ")).collect(Collectors.toList());

        int total = 0;
        int cumulative = 0;
        for (int i = numbersWithSpaces.size() - 1; i >= 0; i--) {
            if (numbersWithSpaces.get(i).contains("-")) {
                if (cumulative != 0) {
                    total += calculateNumberWithDash(numbersWithSpaces.get(i)) * cumulative;
                    cumulative = 0;
                } else {
                    total += calculateNumberWithDash(numbersWithSpaces.get(i));
                }
            } else {
                if (numbersWithSpaces.get(i).equals("hundred") || numbersWithSpaces.get(i).equals("million")) {
                    cumulative = INTEGERS_BY_STRING.get(numbersWithSpaces.get(i));
                } else if (numbersWithSpaces.get(i).equals("thousand")) {
                    int thousandValue = parseInt(numStr.substring(0, numStr.indexOf("thousand"))) * INTEGERS_BY_STRING.get(numbersWithSpaces.get(i));
                    total += thousandValue;
                    i = -1;
                } else if (cumulative != 0) {
                    total += INTEGERS_BY_STRING.get(numbersWithSpaces.get(i)) * cumulative;
                    cumulative = 0;
                } else {
                    total += INTEGERS_BY_STRING.get(numbersWithSpaces.get(i));

                }
            }

        }
        return total;
    }

    private static int calculateNumberWithDash(String numberWithDash) {
        String[] numbers = numberWithDash.split("-");

        int valueWithDash = 0;
        for (String number : numbers) {
            valueWithDash += INTEGERS_BY_STRING.get(number);
        }

        return valueWithDash;
    }
}
