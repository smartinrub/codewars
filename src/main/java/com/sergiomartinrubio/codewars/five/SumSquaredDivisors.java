package com.sergiomartinrubio.codewars.five;

import java.util.*;

public class SumSquaredDivisors {
    public static String listSquared(long m, long n) {
        Map<Long, List<Long>> divisorsByNumber = new HashMap<>();
        for (long number = n; number >= m; number--) {
            for (long j = number; j >= 1; j--) {
                if (number % j == 0) {
                    if (divisorsByNumber.get(number) == null) {
                        List<Long> divisors = new ArrayList<>();
                        divisors.add((long) Math.pow(j, 2));
                        divisorsByNumber.put(number, divisors);
                    } else {
                        divisorsByNumber.get(number).add((long) Math.pow(j, 2));
                    }
                }
            }
        }

        List<Map<Long, Integer>> output = new ArrayList<>();
        for (Map.Entry<Long, List<Long>> entry : divisorsByNumber.entrySet()) {

            int sum = entry.getValue().stream().mapToInt(Long::intValue).sum();

            double sqrt = Math.sqrt(sum);
            if ((sqrt - Math.floor(sqrt)) == 0) {
                output.add(Map.of(entry.getKey(), sum));
            }
        }

        output.sort((o1, o2) -> {
            Collection<Integer> values1 = o1.values();
            Collection<Integer> values2 = o2.values();
            if (!values1.isEmpty() && !values2.isEmpty()) {
                return values1.iterator().next().compareTo(values2.iterator().next());
            } else {
                return 0;
            }
        });

        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < output.size(); i++) {
            Optional<Map.Entry<Long, Integer>> entryPair = output.get(i).entrySet().stream().findFirst();

            entryPair.ifPresent(entry -> result.append("[")
                    .append(entry.getKey())
                    .append(", ").append(entry.getValue())
                    .append("]"));

            if (i != output.size() - 1) {
                result.append(", ");
            }
        }

        result.append("]");

        return result.toString();
    }
}
