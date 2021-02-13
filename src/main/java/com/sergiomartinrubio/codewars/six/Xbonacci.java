package com.sergiomartinrubio.codewars.six;

public class Xbonacci {
    public double[] tribonacci(double[] s, int n) {
        double firstPosition = s[0];
        double secondPosition = s[1];
        double thirdPosition = s[2];

        double[] result = new double[n];
        double current;

        for (int i = 0; i < n; i++) {
            if (i < 3) {
                result[i] = s[i];
            } else {
                current = firstPosition + secondPosition + thirdPosition;
                result[i] = current;
                firstPosition = secondPosition;
                secondPosition = thirdPosition;
                thirdPosition = current;
            }
        }
        return result;
    }
}
