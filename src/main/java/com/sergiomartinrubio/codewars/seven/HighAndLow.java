package com.sergiomartinrubio.codewars.seven;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HighAndLow {
    public static String highAndLow(String numbers) {
        List<Integer> integers = Arrays.stream(Arrays.stream(numbers.split(" "))
                .toArray())
                .map(number -> Integer.parseInt((String) number))
                .collect(Collectors.toList());
        int min = integers.get(0);
        int max = integers.get(0);
        for (int i = 1; i < integers.size(); i++) {
            Integer number = integers.get(i);
            if (number < min) {
                min = number;
            }

            if (number > max) {
                max = number;
            }
        }
        return String.format("%d %d", max, min);
    }
}
