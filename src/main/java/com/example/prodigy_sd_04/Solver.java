package com.example.prodigy_sd_04;

public class Solver {
    private static final int SIZE = 9;

    public boolean isValidGrid(int[][] grid) {
        for (int i = 0; i < SIZE; i++) {
            boolean[] rowCheck = new boolean[SIZE + 1];
            boolean[] colCheck = new boolean[SIZE + 1];
            boolean[] subgridCheck = new boolean[SIZE + 1];

            for (int j = 0; j < SIZE; j++) {
                // Check row
                if (grid[i][j] != 0) {
                    if (rowCheck[grid[i][j]]) {
                        return false;
                    }
                    rowCheck[grid[i][j]] = true;
                }
                // Check column
                if (grid[j][i] != 0) {
                    if (colCheck[grid[j][i]]) {
                        return false;
                    }
                    colCheck[grid[j][i]] = true;
                }
                // Check 3x3 subgrid
                int subgridRow = 3 * (i / 3) + j / 3;
                int subgridCol = 3 * (i % 3) + j % 3;
                if (grid[subgridRow][subgridCol] != 0) {
                    if (subgridCheck[grid[subgridRow][subgridCol]]) {
                        return false;
                    }
                    subgridCheck[grid[subgridRow][subgridCol]] = true;
                }
            }
        }
        return true;
    }

    public boolean solve(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (solve(grid)) {
                                return true;
                            }
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    boolean isSafe(int[][] grid, int row, int col, int num) {
        for (int x = 0; x < SIZE; x++) {
            if (grid[row][x] == num || grid[x][col] == num ||
                    grid[row - row % 3 + x / 3][col - col % 3 + x % 3] == num) {
                return false;
            }
        }
        return true;
    }
}
