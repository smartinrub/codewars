package com.sergiomartinrubio.codewars.two;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class REPL {
    Map<String, Double> valuesByVariable = new HashMap<>();

    public Double input(String input) {
        if (input.isBlank()) {
            return null;
        }

        int bracketCounter = 0;
        List<String> tokens = tokenize(input);

        if (tokens.size() > 1 && tokens.stream().allMatch(REPL::isNumeric)) {
            throw new RuntimeException();
        }

        Stack<Stack<Double>> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();
        double secondArgument;
        String variableToAssign = "";

        for (int i = 0; i < tokens.size(); i++) {
            String argument = tokens.get(i);

            if (tokens.get(i).equals(")")) {
                if (numbers.size() > 1 && numbers.get(numbers.size() - 2).empty()) {
                    numbers.remove(numbers.size() - 2);
                    operators.pop();
                    continue;
                }

                if (numbers.size() == 1) {
                    operators.pop();
                    Stack<Double> numbersGroup = new Stack<>();
                    Double resultGroup = numbers.pop().stream().reduce(0d, Double::sum);
                    numbersGroup.push(resultGroup);
                    numbers.push(numbersGroup);
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

                if (numbers.size() == 1) {
                    bracketCounter = 0;
                    operators.clear();
                    continue;
                }

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
                    if (tokens.get(i).equals("(")) {
                        operators.push("+");
                        Stack<Double> numbersGroup = new Stack<>();
                        numbers.push(numbersGroup);
                    } else if (i < tokens.size() - 1 && tokens.get(i + 1).equals("(")) {
                        operators.push(argument);
                        Stack<Double> numbersGroup = new Stack<>();
                        numbers.push(numbersGroup);
                        i++;
                    } else if (Character.isLetter(argument.charAt(0)) && i < tokens.size() - 1 && tokens.get(i + 1).equals("=")) {
                        variableToAssign = argument;
                        i++;
                    } else {
                        switch (argument) {
                            case "-":
                                if (!tokens.get(i + 1).equals("-")) {
                                    if (numbers.empty()) {
                                        Stack<Double> numbersGroup = new Stack<>();
                                        numbersGroup.push(parseDouble(tokens.get(i + 1)) * -1);
                                        numbers.push(numbersGroup);
                                    } else {
                                        numbers.get(numbers.size() - 1).push(parseDouble(tokens.get(i + 1)) * -1);
                                    }
                                }

                                i++;
                                break;
                            case "*":
                                if (tokens.get(i + 1).equals("-")) {
                                    secondArgument = parseDouble(tokens.get(i + 1) + tokens.get(i + 2));
                                    i++;
                                } else {
                                    secondArgument = parseDouble(tokens.get(i + 1));
                                }
                                numbers.get(numbers.size() - 1).push(numbers.get(numbers.size() - 1).pop() * secondArgument);
                                i++;
                                break;
                            case "/":
                                if (tokens.get(i + 1).equals("-")) {
                                    numbers.get(numbers.size() - 1).push(numbers.get(numbers.size() - 1).pop() * -1);
                                    tokens.remove(i + 1);
                                    i--;
                                } else {
                                    numbers.get(numbers.size() - 1).push(numbers.get(numbers.size() - 1).pop() / parseDouble(tokens.get(i + 1)));
                                    i++;
                                }
                                break;
                            case "%":
                                if (tokens.get(i + 1).equals("-")) {
                                    numbers.get(numbers.size() - 1).push(numbers.get(numbers.size() - 1).pop() * -1);
                                    tokens.remove(i + 1);
                                    i--;
                                } else {
                                    numbers.get(numbers.size() - 1).push(numbers.get(numbers.size() - 1).pop() % parseDouble(tokens.get(i + 1)));
                                    i++;
                                }
                                break;
                            case "+":
                                break;
                            default:
                                if (numbers.empty()) {
                                    Stack<Double> numbersGroup = new Stack<>();
                                    numbersGroup.push(valuesByVariable.get(argument));
                                    numbers.push(numbersGroup);
                                } else {
                                    numbers.get(numbers.size() - 1).push(valuesByVariable.get((argument)));
                                }
                        }
                    }
                }
            }
        }

        if (!variableToAssign.isBlank()) {
            valuesByVariable.putIfAbsent(variableToAssign, 0d);
            valuesByVariable.put(variableToAssign, numbers.stream().flatMap(List::stream).reduce(0d, Double::sum));
        }

        if (!valuesByVariable.isEmpty() && !variableToAssign.isBlank()) {
            return valuesByVariable.get(variableToAssign);
        }

        return numbers.stream().flatMap(List::stream).reduce(0d, Double::sum);
    }

    private static List<String> tokenize(String input) {
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
