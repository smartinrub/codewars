package com.sergiomartinrubio.codewars.six;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortTheOdd {
    public static int[] sortArray(int[] array) {
        List<Integer> sortedOdds = Arrays.stream(array)
                .filter(number -> number % 2 != 0)
                .boxed()
                .sorted()
                .collect(Collectors.toList());

        int sortedOddsIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                array[i] = sortedOdds.get(sortedOddsIndex);
                sortedOddsIndex++;
            }
        }

        return array;
    }
}
