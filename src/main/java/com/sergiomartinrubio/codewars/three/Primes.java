package com.sergiomartinrubio.codewars.three;

import java.util.stream.IntStream;

public class Primes {
    public static IntStream stream() {
        return IntStream.range(2, 100000000)
                .filter(Primes::isPrime);
    }

    private static boolean isPrime(int number) {
        int sqrt = (int) Math.sqrt(number);
        for (int i = 2; i <= sqrt; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
