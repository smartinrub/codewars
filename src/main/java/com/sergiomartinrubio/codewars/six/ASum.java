package com.sergiomartinrubio.codewars.six;

import java.math.BigInteger;

//  n^3 + (n-1)^3 + ... + 1^3 = m
public class ASum {
    public static long findNb(long m) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger n = BigInteger.ONE;

        BigInteger target = BigInteger.valueOf(m);

        while (sum.compareTo(target) < 0) {
            sum = sum.add(n.pow(3));
            n = n.add(BigInteger.ONE);
        }

        if (sum.compareTo(target) == 0) {
            return n.longValue() - 1;
        }

        return -1;
    }
}
