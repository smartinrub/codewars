package com.sergiomartinrubio.codewars.four;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class BefungeInterpreter {
    public String interpret(String code) {
        String[] lines = code.split("\n");

        char[][] grid = new char[3000][3000];
        for (int i = 0; i < lines.length; i++) {
            char[] chars = lines[i].toCharArray();
            System.arraycopy(chars, 0, grid[i], 0, chars.length);
        }

        StringBuilder output = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        int x = 0;
        int y = 0;

        boolean stringMode = false;
        char direction = 'r';

        while (grid[y][x] != '@') {
            char current = grid[y][x];
            if (stringMode) {
                if (current == '"') {
                    stringMode = false;
                } else {
                    stack.push(((int) current));
                }
            } else {
                if (current == '>') {
                    direction = 'r';
                } else if (current == '<') {
                    direction = 'l';
                } else if (current == 'v') {
                    direction = 'd';
                } else if (current == '^') {
                    direction = 'u';
                } else if (Character.isDigit(current)) {
                    stack.push(Character.getNumericValue(current));
                } else if (current == '.') {
                    output.append(stack.pop());
                } else if (current == '_') {
                    Integer value = stack.pop();
                    if (value == 0) {
                        direction = 'r';
                    } else {
                        direction = 'l';
                    }
                } else if (current == '|') {
                    Integer value = stack.pop();
                    if (value == 0) {
                        direction = 'd';
                    } else {
                        direction = 'u';
                    }
                } else if (current == ':') {
                    if (stack.empty()) {
                        stack.push(0);
                    } else {
                        stack.push(stack.peek());
                    }
                } else if (current == '*') {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a * b);
                } else if (current == '+') {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a + b);
                } else if (current == '-') {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b - a);
                } else if (current == '/') {
                    int a = stack.pop();

                    if (a == 0) {
                        stack.push(0);
                    } else {
                        int b = stack.pop();
                        stack.push(b / a);
                    }

                } else if (current == '%') {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a == 0 ? 0 : b % a);
                } else if (current == '$') {
                    stack.pop();
                } else if (current == '\\') {
                    Integer a = stack.pop();
                    if (stack.size() == 1) {
                        stack.push(a);
                        stack.push(0);
                    } else {
                        Integer b = stack.pop();
                        stack.push(a);
                        stack.push(b);
                    }

                } else if (current == '!') {
                    Integer value = stack.pop();
                    stack.push(value == 0 ? 1 : 0);
                } else if (current == '`') {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b > a ? 1 : 0);
                } else if (current == '?') {
                    List<Character> givenList = List.of('r', 'l', 'u', 'd');
                    Random rand = new Random();
                    direction = givenList.get(rand.nextInt(givenList.size()));
                } else if (current == '#') {
                    switch (direction) {
                        case 'r':
                            x++;
                            break;
                        case 'l':
                            x--;
                            break;
                        case 'u':
                            y--;
                            break;
                        case 'd':
                            y++;
                            break;
                    }
                } else if (current == 'p') {
                    Integer yValue = stack.pop();
                    Integer xValue = stack.pop();
                    Integer vValue = stack.pop();
                    grid[yValue][xValue] = (char) vValue.intValue();
                } else if (current == 'g') {
                    Integer yValue = stack.pop();
                    Integer xValue = stack.pop();
                    stack.push((int) grid[yValue][xValue]);
                } else if (current == ',') {
                    output.append((char) stack.pop().intValue());
                } else if (current == '"') {
                    stringMode = true;
                }

            }
            switch (direction) {
                case 'r':
                    x++;
                    break;
                case 'l':
                    x--;
                    break;
                case 'u':
                    y--;
                    break;
                case 'd':
                    y++;
                    break;
            }
        }

        return output.toString();
    }
}
