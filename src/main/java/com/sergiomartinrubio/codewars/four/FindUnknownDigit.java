package com.sergiomartinrubio.codewars.four;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FindUnknownDigit {
    public static int solveExpression(final String expression) {
        String number1;
        char operator;
        String number2;
        String number3;

        int index = 0;
        if (expression.charAt(0) == '-') {
            index = 1;
        }

        while (expression.charAt(index) != '+' && expression.charAt(index) != '-' && expression.charAt(index) != '*') {
            index++;
        }

        number1 = expression.substring(0, index);
        operator = expression.charAt(index);
        index++;

        int indexNumber2 = index;
        if (expression.charAt(index) == '-') {
            index++;
        }

        while (expression.charAt(index) != '=') {
            index++;
        }

        number2 = expression.substring(indexNumber2, index);
        index++;

        number3 = expression.substring(index);

        List<String> numbers = List.of(number1, number2, number3);

        List<String> replacedNumbers = new ArrayList<>(numbers);
        for (int i = 0; i <= 9; i++) {

            if (expression.indexOf(String.valueOf(i).charAt(0)) != -1) {
                continue;
            }

            // Replace ? with number
            for (int j = 0; j < numbers.size(); j++) {
                replacedNumbers.set(j, numbers.get(j).replaceAll("\\?", String.valueOf(i)));
            }

            boolean isDoubleZero = false;
            for (String replacedNumber : replacedNumbers) {
                if (Math.abs(Integer.parseInt(replacedNumber)) == 0 && replacedNumber.length() > 1) {
                    isDoubleZero = true;
                }
            }
            if (isDoubleZero) {
                continue;
            }

            Pattern pattern = Pattern.compile("^0{2,}[0-9]*|^-0+[0-9]*");
            boolean invalidNumber = replacedNumbers.stream()
                    .anyMatch(number -> pattern.matcher(number).find());
            if (invalidNumber) {
                continue;
            }

            int firstReplacedNumber = Integer.parseInt(replacedNumbers.get(0));
            int secondReplacedNumber = Integer.parseInt(replacedNumbers.get(1));
            int thirdReplacedNumber = Integer.parseInt(replacedNumbers.get(2));

            switch (operator) {
                case '+':
                    if (firstReplacedNumber + secondReplacedNumber == thirdReplacedNumber) {
                        return i;
                    }
                    break;
                case '-':
                    if (firstReplacedNumber - secondReplacedNumber == thirdReplacedNumber) {
                        return i;
                    }
                    break;
                case '*':
                    if (firstReplacedNumber * secondReplacedNumber == thirdReplacedNumber) {
                        return i;
                    }
                    break;
            }
        }

        return -1;
    }
}
