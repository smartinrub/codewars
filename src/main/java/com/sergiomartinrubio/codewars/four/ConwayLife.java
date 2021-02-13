package com.sergiomartinrubio.codewars.four;

import java.util.Arrays;

public class ConwayLife {
    public static int[][] getGeneration(int[][] cells, int generations) {
        int[][] board = new int[cells.length + 2][cells[0].length + 2];

        for (int i = 0; i < cells.length; i++) {
            System.arraycopy(cells[i], 0, board[i + 1], 1, cells[i].length);
        }

        for (int i = 0; i < generations; i++) {

            int[][] copyBoard = new int[board.length][board[0].length];
            for (int q = 0; q < board.length; q++) {
                copyBoard[q] = Arrays.copyOf(board[q], board[q].length);
            }

            int rowsIncreased = 0;
            int columnsIncreased = 0;

            for (int j = 0; j < board.length; j++) {
                for (int k = 0; k < board[j].length; k++) {
                    int aliveNeighbours = 0;
                    if (j != 0 && j != board.length - 1 && k != 0 && k != board[j].length - 1) {
                        if (board[j - 1][k - 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j][k - 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j + 1][k - 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j - 1][k] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j + 1][k] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j - 1][k + 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j][k + 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j + 1][k + 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j][k] == 1 && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
                            copyBoard[j + rowsIncreased][k + columnsIncreased] = 0;
                        } else if (board[j][k] == 0 && aliveNeighbours == 3) {
                            copyBoard[j + rowsIncreased][k + columnsIncreased] = 1;
                        }

                    } else if (j == 0 && k != 0 && k != board[j].length - 1) {
                        if (board[j + 1][k - 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j + 1][k] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j + 1][k + 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j][k] == 1 && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
                            copyBoard[j + rowsIncreased][k] = 0;

                            copyBoard = expandBoardByOneRow(copyBoard, 'T');
                            rowsIncreased++;
                        } else if (board[j][k] == 0 && aliveNeighbours == 3) {
                            copyBoard[j + rowsIncreased][k] = 1;
                            copyBoard = expandBoardByOneRow(copyBoard, 'T');
                            rowsIncreased++;
                        }
                    } else if (j == board.length - 1 && k != 0 && k != board[j].length - 1) {
                        if (board[j - 1][k - 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j - 1][k] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j - 1][k + 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j][k] == 1 && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
                            copyBoard[j + rowsIncreased][k] = 0;
                            copyBoard = expandBoardByOneRow(copyBoard, 'B');
                            rowsIncreased++;
                        } else if (board[j][k] == 0 && aliveNeighbours == 3) {
                            copyBoard[j + rowsIncreased][k] = 1;
                            copyBoard = expandBoardByOneRow(copyBoard, 'B');
                            rowsIncreased++;
                        }
                    } else if (k == 0 && j != 0 && j != board.length - 1) {
                        if (board[j - 1][k + 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j][k + 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j + 1][k + 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j][k] == 1 && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
                            copyBoard[j][k + columnsIncreased] = 0;

                            copyBoard = expandBoardByOneColumn(copyBoard, 'L');
                            columnsIncreased++;
                        } else if (board[j][k] == 0 && aliveNeighbours == 3) {
                            copyBoard[j][k + columnsIncreased] = 1;
                            copyBoard = expandBoardByOneColumn(copyBoard, 'L');
                            columnsIncreased++;
                        }
                    } else if (k == board[j].length - 1 && j != 0 && j != board.length - 1) {
                        if (board[j - 1][k - 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j][k - 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j + 1][k - 1] == 1) {
                            aliveNeighbours++;
                        }

                        if (board[j][k] == 1 && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
                            copyBoard[j][k + columnsIncreased] = 0;

                            copyBoard = expandBoardByOneColumn(copyBoard, 'R');
                            columnsIncreased++;
                        } else if (board[j][k] == 0 && aliveNeighbours == 3) {
                            copyBoard[j][k + columnsIncreased] = 1;
                            copyBoard = expandBoardByOneColumn(copyBoard, 'R');
                            columnsIncreased++;
                        }
                    }

                }
            }

            board = copyBoard;
        }


        int linesToRemove = 0;
        boolean lineWithValuesFound = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    lineWithValuesFound = true;
                    break;
                }
            }
            if (lineWithValuesFound) {
                break;
            }
            linesToRemove++;
        }

        int columnsToRemove = 0;
        boolean columnWithValuesFound = false;
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != 0) {
                    columnWithValuesFound = true;
                    break;
                }
            }
            if (columnWithValuesFound) {
                break;
            }
            columnsToRemove++;
        }

        int[][] result = new int[board.length - linesToRemove - 1][board[0].length - columnsToRemove - 1];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = board[i + linesToRemove][j + columnsToRemove];
            }
        }

        return result;
    }

    private static int[][] expandBoardByOneColumn(int[][] board, char newColumnPosition) {
        int[][] expandedBoard = new int[board.length][board[0].length + 1];

        switch (newColumnPosition) {
            case 'L':
                for (int l = 0; l < board.length; l++) {
                    for (int m = 0; m < board[l].length; m++) {
                        expandedBoard[l][m + 1] = board[l][m];
                    }
                }
                break;
            case 'R':
                for (int l = 0; l < board.length; l++) {
                    for (int m = 0; m < board[l].length; m++) {
                        expandedBoard[l][m] = board[l][m];
                    }
                }
                break;
        }

        return expandedBoard;
    }

    private static int[][] expandBoardByOneRow(int[][] board, char newRowPosition) {
        int[][] expandedBoard = new int[board.length + 1][board[0].length];

        switch (newRowPosition) {
            case 'T':
                for (int l = 0; l < board.length; l++) {
                    for (int m = 0; m < board[l].length; m++) {
                        expandedBoard[l + 1][m] = board[l][m];
                    }
                }
                break;
            case 'B':
                for (int l = 0; l < board.length; l++) {
                    for (int m = 0; m < board[l].length; m++) {
                        expandedBoard[l][m] = board[l][m];
                    }
                }
                break;
        }

        return expandedBoard;
    }


}
