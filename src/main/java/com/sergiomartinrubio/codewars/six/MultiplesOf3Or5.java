package com.sergiomartinrubio.codewars.six;

import java.util.HashSet;
import java.util.Set;

public class MultiplesOf3Or5 {

    public int solution(int number) {
        Set<Integer> numbers = new HashSet<>();

        if (number < 0) {
            return 0;
        }

        for (int i = number - 1; i > 0; i--) {
            if (i % 3 == 0 || i % 5 == 0) {
                numbers.add(i);
            }
        }
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
