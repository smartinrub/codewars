package com.sergiomartinrubio.codewars.two;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AssemblerInterpreter {
    private static Map<Character, Integer> valueByRegister;
    private static Map<String, List<String>> subroutines;
    private static int lastCompareValue;
    private static StringBuilder output;
    private static Stack<Integer> subroutineIndexes;
    private static boolean hasEnd;
    private static boolean isSubroutineReturned;

    public static String interpret(final String input) {
        hasEnd = false;
        isSubroutineReturned = false;
        output = new StringBuilder();
        valueByRegister = new HashMap<>();
        subroutineIndexes = new Stack<>();
        List<String> lines = removeComments(input.split("\n"));
        subroutines = storeSubroutines(lines);

        processLines(removeEmptySpaces(lines));

        return !hasEnd ? null : output.toString();
    }

    private static List<String> removeComments(String[] lines) {
        List<String> sanitizedLines = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(";")) {
                line = line.substring(0, line.indexOf(";"));
            }

            if (!line.isBlank()) {
                sanitizedLines.add(line);
            }
        }
        return sanitizedLines;
    }

    private static Map<String, List<String>> storeSubroutines(List<String> lines) {
        Map<String, List<String>> linesBySubroutine = new HashMap<>();
        int startLineIndex = 0;
        int endLineIndex;
        boolean isBeginning = false;
        String subroutineName = "";
        Pattern subroutinePattern = Pattern.compile("[a-z0-9_]+:");

        for (int i = 0; i < lines.size(); i++) {
            if (subroutinePattern.matcher(lines.get(i)).find() && !lines.get(i).startsWith(" ") && !isBeginning) {
                subroutineName = lines.get(i).substring(0, lines.get(i).length() - 1);
                startLineIndex = i;
                isBeginning = true;
            } else if ((lines.get(i).contains("ret") || !lines.get(i).startsWith(" ")) && isBeginning
                    || (i == lines.size() - 1 && isBeginning)) {
                if (lines.get(i).contains("ret")) {
                    endLineIndex = i;
                } else {
                    endLineIndex = i - 1;
                }
                List<String> subroutineLines = lines.stream()
                        .map(String::trim)
                        .skip(startLineIndex + 1)
                        .limit(endLineIndex - startLineIndex)
                        .collect(Collectors.toList());
                linesBySubroutine.put(subroutineName, subroutineLines);
                isBeginning = false;

                if (i != lines.size() - 1) {
                    i--;
                }
            }
        }
        return linesBySubroutine;
    }

    private static List<String> removeEmptySpaces(List<String> lines) {
        return lines.stream().map(String::trim)
                .collect(Collectors.toList());
    }

    private static void processLines(List<String> lines) {
        boolean shouldBreak = false;
        for (int i = 0; i < lines.size(); i++) {
            String instruction = lines.get(i).split(" ")[0];
            switch (instruction) {
                case "mov":
                    copy(lines.get(i));
                    shouldBreak = true;
                    break;
                case "inc":
                    increase(lines.get(i));
                    break;
                case "dec":
                    decrease(lines.get(i));
                    break;
                case "add":
                    add(lines.get(i));
                    break;
                case "sub":
                    sub(lines.get(i));
                    break;
                case "call": {
                    isSubroutineReturned = false;
                    String subroutineName = getSubroutineName(lines.get(i));
                    subroutineIndexes.push(i);
                    processSubroutine(subroutineName);
                    i = subroutineIndexes.pop();
                    break;
                }
                case "ret":
                    isSubroutineReturned = true;
                    shouldBreak = true;
                case "div":
                    divide(lines.get(i));
                    break;
                case "mul":
                    multiply(lines.get(i));
                    break;
                case "msg":
                    storeOutput(lines.get(i));
                    break;
                case "cmp":
                    lastCompareValue = compare(lines.get(i));
                    break;
                case "je":
                    if (lastCompareValue == 0) {
                        processSubroutine(getSubroutineName(lines.get(i)));
                        shouldBreak = true;
                    }
                    break;
                case "jne":
                    if (lastCompareValue != 0) {
                        processSubroutine(getSubroutineName(lines.get(i)));
                        shouldBreak = true;
                    }
                    break;
                case "jl":
                    if (lastCompareValue < 0) {
                        processSubroutine(getSubroutineName(lines.get(i)));
                        shouldBreak = true;
                    }
                    break;
                case "jle":
                    if (lastCompareValue <= 0) {
                        processSubroutine(getSubroutineName(lines.get(i)));
                        shouldBreak = true;
                    }
                    break;
                case "jg":
                    if (lastCompareValue > 0) {
                        processSubroutine(getSubroutineName(lines.get(i)));
                        shouldBreak = true;
                    }
                    break;
                case "jge":
                    if (lastCompareValue >= 0) {
                        processSubroutine(getSubroutineName(lines.get(i)));
                        shouldBreak = true;
                    }
                    break;
                case "jmp": {
                    String subroutineName = getSubroutineName(lines.get(i));
                    processSubroutine(subroutineName);
                    shouldBreak = true;
                    break;
                }
                case "end":
                    if (isSubroutineReturned) {
                        hasEnd = true;
                    }
                    shouldBreak = true;
            }

            if (shouldBreak) {
                break;
            }
        }
    }

    private static String getSubroutineName(String line) {
        return line.substring(line.indexOf(" ")).trim();
    }

    private static void add(String line) {
        String[] arguments = line.substring(line.indexOf(" ")).trim().split(", ");
        String x = arguments[0];
        String y = arguments[1];

        if (y.chars().allMatch(Character::isDigit)) {
            if (valueByRegister.containsKey(x.charAt(0))) {
                valueByRegister.merge(x.charAt(0), 0, (oldValue, newValue) -> oldValue + Integer.parseInt(y));
            }
        } else {
            valueByRegister.merge(x.charAt(0), 0, (oldValue, newValue) -> oldValue + valueByRegister.get(y.charAt(0)));
        }
    }

    private static void sub(String line) {
        String[] arguments = line.substring(line.indexOf(" ")).trim().split(", ");
        String x = arguments[0];
        String y = arguments[1];

        if (y.chars().allMatch(Character::isDigit)) {
            if (valueByRegister.containsKey(x.charAt(0))) {
                valueByRegister.merge(x.charAt(0), 0, (oldValue, newValue) -> oldValue - Integer.parseInt(y));
            }
        } else {
            valueByRegister.merge(x.charAt(0), 0, (oldValue, newValue) -> oldValue - valueByRegister.get(y.charAt(0)));
        }
    }

    private static int compare(String line) {
        String[] arguments = line.substring(line.indexOf(" ")).trim().split(", ");
        String x = arguments[0];
        String y = arguments[1];

        int xValue;
        int yValue;
        if (x.chars().allMatch(Character::isDigit)) {
            xValue = Integer.parseInt(x);
        } else {
            xValue = valueByRegister.get(x.charAt(0));
        }

        if (y.chars().allMatch(Character::isDigit)) {
            yValue = Integer.parseInt(y);
        } else {
            yValue = valueByRegister.get(y.charAt(0));
        }

        return Integer.compare(xValue, yValue);
    }

    private static void multiply(String line) {
        String[] arguments = line.substring(line.indexOf(" ")).trim().split(", ");
        String x = arguments[0];
        String y = arguments[1];

        if (y.chars().allMatch(Character::isDigit)) {
            if (valueByRegister.containsKey(x.charAt(0))) {
                valueByRegister.merge(x.charAt(0), 0, (oldValue, newValue) -> oldValue * Integer.parseInt(y));
            }
        } else {
            valueByRegister.merge(x.charAt(0), 0, (oldValue, newValue) -> oldValue * valueByRegister.get(y.charAt(0)));
        }
    }

    private static void divide(String line) {
        String[] arguments = line.substring(line.indexOf(" ")).trim().split(", ");
        String x = arguments[0];
        String y = arguments[1];

        if (y.chars().allMatch(Character::isDigit)) {
            if (valueByRegister.containsKey(x.charAt(0))) {
                valueByRegister.merge(x.charAt(0), 0, (oldValue, newValue) -> oldValue / Integer.parseInt(y));
            }
        } else {
            valueByRegister.merge(x.charAt(0), 0, (oldValue, newValue) -> oldValue / valueByRegister.get(y.charAt(0)));
        }
    }

    private static void copy(String line) {
        String[] arguments = line.substring(line.indexOf(" ")).trim().split(", ");
        String x = arguments[0];
        String y = arguments[1];

        if (y.chars().allMatch(Character::isDigit)) {
            valueByRegister.put(x.charAt(0), Integer.valueOf(y));
        } else {
            valueByRegister.put(x.charAt(0), valueByRegister.get(y.charAt(0)));
        }
    }

    private static void increase(String line) {
        Character x = line.substring(line.indexOf(" ")).trim().charAt(0);
        if (valueByRegister.containsKey(x)) {
            valueByRegister.merge(x, 1, Integer::sum);
        }
    }

    private static void decrease(String line) {
        Character x = line.substring(line.indexOf(" ")).trim().charAt(0);
        if (valueByRegister.containsKey(x)) {
            valueByRegister.merge(x, 0, (oldValue, newValue) -> oldValue - 1);
        }
    }

    private static void processSubroutine(String subroutineName) {
        List<String> subroutineLines = subroutines.get(subroutineName);
        processLines(subroutineLines);
    }

    private static void storeOutput(String line) {
        String[] arguments = line.substring(line.indexOf(" "))
                .trim()
                .split("(?<!'), |, (?!')");
        for (String argument : arguments) {
            if (argument.charAt(0) == '\'' && argument.charAt(argument.length() - 1) == '\'') {
                output.append(argument, 1, argument.length() - 1);
            } else {
                output.append(valueByRegister.get(argument.charAt(0)));
            }
        }
    }
}
