package com.sergiomartinrubio.codewars.two;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerSquareRoot {
    public static String integerSquareRoot(String n) {
        String[] reversedNumbers = new StringBuilder(n).reverse().toString().split("(?<=\\G..)");
        List<String> numbers = Arrays.stream(reversedNumbers)
                .map(number -> new StringBuilder(number).reverse().toString())
                .collect(Collectors.toList());
        Collections.reverse(numbers);

        String subtractAmount = "";
        StringBuilder result = new StringBuilder();
        long remainder;
        int i = 0;
        subtractAmount += numbers.get(i);
        long perfectSquare = getPerfectSquare(Long.parseLong(subtractAmount));
        result.append(perfectSquare);
        remainder = Long.parseLong(subtractAmount) - perfectSquare * perfectSquare;
        subtractAmount = String.valueOf(remainder);
        i++;

        while (i < numbers.size()) {
            subtractAmount += numbers.get(i);
            String prefix = multiply(result.toString(), String.valueOf(2));
            long number = findNumber(prefix, subtractAmount);
            String numberToSubtract = multiply(prefix + number, String.valueOf(number));
            subtractAmount = subtract(subtractAmount, numberToSubtract);

            result.append(number);
            i++;
        }

        return result.toString();
    }

    public static long getPerfectSquare(long number) {
        return (long) Math.sqrt(number);
    }

    private static String multiply(String firstNumber, String secondNumber) {
        int lengthFirstNumber = firstNumber.length();
        int lengthSecondNumber = secondNumber.length();
        if (lengthFirstNumber == 0 || lengthSecondNumber == 0)
            return "0";

        int[] result = new int[lengthFirstNumber + lengthSecondNumber];

        int firstIndex = 0;
        int secondIndex;

        for (int i = lengthFirstNumber - 1; i >= 0; i--) {
            int carry = 0;
            int n1 = firstNumber.charAt(i) - '0';

            secondIndex = 0;

            for (int j = lengthSecondNumber - 1; j >= 0; j--) {
                int n2 = secondNumber.charAt(j) - '0';
                int sum = n1 * n2 + result[firstIndex + secondIndex] + carry;
                carry = sum / 10;
                result[firstIndex + secondIndex] = sum % 10;
                secondIndex++;
            }

            if (carry > 0) {
                result[firstIndex + secondIndex] += carry;
            }

            firstIndex++;
        }

        int i = result.length - 1;
        while (i >= 0 && result[i] == 0) {
            i--;
        }

        if (i == -1) {
            return "0";
        }

        StringBuilder s = new StringBuilder();
        while (i >= 0) {
            s.append(result[i--]);
        }

        return s.toString();
    }

    private static long findNumber(String numberPrefix, String subtractAmount) {
        for (long i = 9; i > 0; i--) {
            String numberWithPrefix = numberPrefix + i;

            if (!compareTwoStringNumbers(multiply(numberWithPrefix, String.valueOf(i)), subtractAmount.replaceFirst("^0+(?!$)", ""))) {
                return i;
            }
        }
        return 0;
    }

    private static boolean compareTwoStringNumbers(String firstNumber, String secondNumber) {
        if (firstNumber.length() > secondNumber.length()) {
            return true;
        } else if (firstNumber.length() < secondNumber.length()) {
            return false;
        } else {
            for (int i = 0; i < firstNumber.length(); i++) {
                if (Character.getNumericValue(firstNumber.charAt(i)) > Character.getNumericValue(secondNumber.charAt(i))) {
                    return true;
                } else if (Character.getNumericValue(firstNumber.charAt(i)) < Character.getNumericValue(secondNumber.charAt(i))) {
                    return false;
                }
            }
        }
        return false;
    }

    private static String subtract(String firstNumber, String secondNumber) {
        int lengthDiff = firstNumber.length() - secondNumber.length();

        secondNumber = "0".repeat(Math.max(0, lengthDiff)) + secondNumber;

        List<Integer> firstNumberDigits = firstNumber.chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());
        List<Integer> secondNumberDigits = secondNumber.chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());


        if (secondNumber.replaceFirst("^0+(?!$)", "").equals("")) {
            return firstNumber;
        }

        StringBuilder result = new StringBuilder();
        for (int i = firstNumberDigits.size() - 1; i >= 0; i--) {
            if (i > secondNumberDigits.size()) {
                result.insert(0, firstNumberDigits.get(i));
                continue;
            }

            if (firstNumberDigits.get(i) >= secondNumberDigits.get(i)) {
                result.insert(0, firstNumberDigits.get(i) - secondNumberDigits.get(i));
            } else {
                if (i > 0) {
                    Integer nextDigit = firstNumberDigits.get(i - 1);
                    firstNumberDigits.set(i - 1, nextDigit - 1);
                    result.insert(0, firstNumberDigits.get(i) + 10 - secondNumberDigits.get(i));
                }
            }
        }
        return result.toString().replaceFirst("^0+(?!$)", "");
    }
}
