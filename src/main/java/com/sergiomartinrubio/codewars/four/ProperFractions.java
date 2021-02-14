package com.sergiomartinrubio.codewars.four;

import java.util.LinkedList;
import java.util.List;

public class ProperFractions {

    // Too Slow
    public static long properFractions(long n) {
        long startTime = System.nanoTime();
        // check if prime number
        boolean isPrime = true;
        for (int i = 2; i <= n / 2; ++i) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Check if PRIME: " + totalTime / 1000000000f + "s");

        long count = 0;
        if (isPrime) {
            return n - 1;
        } else {
            startTime = System.nanoTime();
            // get divisors
            List<Long> divisors = new LinkedList<>();

            for (long i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    if (isNoSkip(divisors, i)) {
                        divisors.add(i);
                    }
                }
            }

            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            System.out.println("Get DIVISORS: " + totalTime / 1000000000f + "s");

            long valueSum;
            long multiplier;

            count += n / divisors.get(0);

            startTime = System.nanoTime();
            for (int i = 1; i < divisors.size(); i++) {
                valueSum = 0;
                multiplier = divisors.get(i);
                while (valueSum < n) {
                    valueSum += multiplier;
                    if (isNoSkip(divisors.subList(0, i), valueSum)) {
                        count++;
                    }
                }
            }
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            System.out.println("Get COUNT: " + totalTime / 1000000000f + "s");
        }

        return n - count;
    }

    private static boolean isNoSkip(List<Long> divisors, long position) {
        for (Long divisor : divisors) {
            if (position % divisor == 0) {
                return false;
            }
        }
        return true;
    }

}
