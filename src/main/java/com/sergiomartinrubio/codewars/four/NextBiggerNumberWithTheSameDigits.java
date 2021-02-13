package com.sergiomartinrubio.codewars.four;

import java.util.*;
import java.util.stream.Collectors;

public class NextBiggerNumberWithTheSameDigits {
    public static long nextBiggerNumber(long n) {
        long copyOriginal = n;
        List<Long> digits = new ArrayList<>();
        while (n > 0) {
            digits.add(n % 10);
            n = n / 10;
        }

        Collections.reverse(digits);

        for (int i = digits.size() - 1; i > 0; i--) {
            List<Long> fraction = digits.subList(i - 1, digits.size());
            Set<Long> permutations = new HashSet<>();
            for (int j = 0; j < 500; j++) {
                ArrayList<Long> newList = new ArrayList<>(fraction);
                Collections.shuffle(newList);
                String newDigit = newList.stream().map(String::valueOf).collect(Collectors.joining());
                permutations.add(Long.parseLong(newDigit));
            }
            List<Long> sortedPermutations = permutations.stream().sorted().collect(Collectors.toList());
            Long longFraction = Long.parseLong(fraction.stream().map(String::valueOf).collect(Collectors.joining()));

            Optional<Long> greaterPermutation = sortedPermutations.stream()
                    .filter(permutation -> permutation > longFraction)
                    .findFirst();
            if (greaterPermutation.isPresent()) {
                return copyOriginal - longFraction + greaterPermutation.get();
            }
        }

        return -1;
    }

}
