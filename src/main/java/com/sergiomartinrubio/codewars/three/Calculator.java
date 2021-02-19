package com.sergiomartinrubio.codewars.three;

public class Calculator {
    public static Double evaluate(String expression) {
        String[] operandsAndNumbers = expression.split(" ");

        double operationResult;
        for (int i = 1; i < operandsAndNumbers.length; i++) {
            if ("*".equals(operandsAndNumbers[i])) {
                operationResult = Double.parseDouble(operandsAndNumbers[i - 1]) * Double.parseDouble(operandsAndNumbers[i + 1]);
                operandsAndNumbers[i + 1] = String.valueOf(operationResult);
                operandsAndNumbers = resizeArray(operandsAndNumbers, i + 1);
                i--;
            } else if ("/".equals(operandsAndNumbers[i])) {
                operationResult = Double.parseDouble(operandsAndNumbers[i - 1]) / Double.parseDouble(operandsAndNumbers[i + 1]);
                operandsAndNumbers[i + 1] = String.valueOf(operationResult);
                operandsAndNumbers = resizeArray(operandsAndNumbers, i + 1);
                i--;
            }
        }

        for (int i = 1; i < operandsAndNumbers.length; i++) {
            if ("+".equals(operandsAndNumbers[i])) {
                operationResult = Double.parseDouble(operandsAndNumbers[i - 1]) + Double.parseDouble(operandsAndNumbers[i + 1]);
                operandsAndNumbers[i + 1] = String.valueOf(operationResult);
                operandsAndNumbers = resizeArray(operandsAndNumbers, i + 1);
                i--;
            } else if ("-".equals(operandsAndNumbers[i])) {
                operationResult = Double.parseDouble(operandsAndNumbers[i - 1]) - Double.parseDouble(operandsAndNumbers[i + 1]);
                operandsAndNumbers[i + 1] = String.valueOf(operationResult);
                operandsAndNumbers = resizeArray(operandsAndNumbers, i + 1);
                i--;
            }
        }

        return Double.parseDouble(operandsAndNumbers[0]);
    }

    private static String[] resizeArray(String[] operandsAndNumbers, int initialPosition) {
        String[] resizedArray = new String[operandsAndNumbers.length - 2];

        for (int i = 0; i < initialPosition - 2; i++) {
            resizedArray[i] = operandsAndNumbers[i];
        }

        for (int i = initialPosition; i < operandsAndNumbers.length; i++) {
            resizedArray[i - 2] = operandsAndNumbers[i];
        }

        return resizedArray;
    }

}
