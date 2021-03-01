package com.sergiomartinrubio.codewars.two;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class MathEvaluator {

    public double calculate(String expression) {
        int bracketCounter = 0;
        List<String> arguments = getArguments(expression);
        double secondArgument;

        Stack<Stack<Double>> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();
        for (int i = 0; i < arguments.size(); i++) {
            String argument = arguments.get(i);

            if (arguments.get(i).equals(")")) {
                if (numbers.size() > 1 && numbers.get(numbers.size() - 2).empty()) {
                    numbers.remove(numbers.size() - 2);
                    operators.pop();
                    continue;
                }

                if (numbers.size() == 1) {
                    operators.pop();
                    continue;
                }

                if (operators.peek().equals("-")) {
                    Double resultPreviousGroup = numbers.pop().stream().reduce(0d, Double::sum);
                    Stack<Double> numbersGroup = new Stack<>();
                    numbersGroup.push(resultPreviousGroup * -1);
                    numbers.push(numbersGroup);
                    operators.pop();
                    operators.push("+");
                    bracketCounter++;
                    continue;
                }

                Double resultPreviousGroup = numbers.pop().stream().reduce(0d, Double::sum);
                Double resultCurrentGroup = numbers.pop().stream().reduce(0d, Double::sum);
                String operator = operators.pop();

                Stack<Double> numbersGroup = new Stack<>();
                switch (operator) {
                    case "-":
                        numbersGroup.push(resultCurrentGroup - resultPreviousGroup);
                        break;
                    case "+":
                        numbersGroup.push(resultCurrentGroup + resultPreviousGroup);
                        break;
                    case "*":
                        numbersGroup.push(resultCurrentGroup * resultPreviousGroup);
                        break;
                    case "/":
                        numbersGroup.push(resultCurrentGroup / resultPreviousGroup);
                        break;

                }
                numbers.push(numbersGroup);

                while (bracketCounter > 0) {
                    resultPreviousGroup = numbers.pop().stream().reduce(0d, Double::sum);
                    resultCurrentGroup = numbers.pop().stream().reduce(0d, Double::sum);
                    operator = operators.pop();

                    numbersGroup = new Stack<>();
                    switch (operator) {
                        case "-":
                            numbersGroup.push(resultCurrentGroup - resultPreviousGroup);
                            break;
                        case "+":
                            numbersGroup.push(resultCurrentGroup + resultPreviousGroup);
                            break;
                        case "*":
                            numbersGroup.push(resultCurrentGroup * resultPreviousGroup);
                            break;
                        case "/":
                            numbersGroup.push(resultCurrentGroup / resultPreviousGroup);
                            break;

                    }
                    numbers.push(numbersGroup);
                    bracketCounter--;
                }

            } else {
                if (isNumeric(argument)) {
                    if (numbers.empty()) {
                        Stack<Double> numbersGroup = new Stack<>();
                        numbersGroup.push(parseDouble(argument));
                        numbers.push(numbersGroup);
                    } else {
                        numbers.get(numbers.size() - 1).push(parseDouble(argument));
                    }

                } else {
                    if (arguments.get(i).equals("(")) {
                        operators.push("+");
                        Stack<Double> numbersGroup = new Stack<>();
                        numbers.push(numbersGroup);
                    } else if (arguments.get(i + 1).equals("(")) {
                        operators.push(argument);
                        Stack<Double> numbersGroup = new Stack<>();
                        numbers.push(numbersGroup);
                        i++;
                    } else {
                        switch (argument) {
                            case "-":
                                if (!arguments.get(i + 1).equals("-")) {
                                    if (numbers.empty()) {
                                        Stack<Double> numbersGroup = new Stack<>();
                                        numbersGroup.push(parseDouble(arguments.get(i + 1)) * -1);
                                        numbers.push(numbersGroup);
                                    } else {
                                        numbers.get(numbers.size() - 1).push(parseDouble(arguments.get(i + 1)) * -1);
                                    }
                                }

                                i++;
                                break;
                            case "*":
                                if (arguments.get(i + 1).equals("-")) {
                                    secondArgument = parseDouble(arguments.get(i + 1) + arguments.get(i + 2));
                                    i++;
                                } else {
                                    secondArgument = parseDouble(arguments.get(i + 1));
                                }
                                numbers.get(numbers.size() - 1).push(numbers.get(numbers.size() - 1).pop() * secondArgument);
                                i++;
                                break;
                            case "/":
                                if (arguments.get(i + 1).equals("-")) {
                                    numbers.get(numbers.size() - 1).push(numbers.get(numbers.size() - 1).pop() * -1);
                                    arguments.remove(i + 1);
                                    i--;
                                } else {
                                    numbers.get(numbers.size() - 1).push(numbers.get(numbers.size() - 1).pop() / parseDouble(arguments.get(i + 1)));
                                    i++;
                                }
                                break;

                        }
                    }

                }

            }
        }

        return numbers.stream().flatMap(List::stream).reduce(0d, Double::sum);
    }

    private static List<String> getArguments(String input) {
        List<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("=>|[-+*/%=\\(\\)]|[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)");
        Matcher m = pattern.matcher(input);
        while (m.find()) {
            tokens.add(m.group());
        }
        return tokens;
    }

    public static boolean isNumeric(String str) {
        try {
            parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
