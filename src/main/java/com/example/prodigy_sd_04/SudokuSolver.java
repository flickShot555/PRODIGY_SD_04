package com.example.prodigy_sd_04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolver extends JFrame {
    private static final int SIZE = 9;
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private Solver solver = new Solver();

    public SudokuSolver() {
        setTitle("Sudoku Solver");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                gridPanel.add(cells[i][j]);
            }
        }

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] grid = new int[SIZE][SIZE];
                if (!readGrid(grid)) {
                    return; // Invalid input
                }

                if (!solver.isValidGrid(grid)) {
                    JOptionPane.showMessageDialog(null, "Invalid Sudoku puzzle");
                    return;
                }

                if (solver.solve(grid)) {
                    displayGrid(grid);
                } else {
                    JOptionPane.showMessageDialog(null, "No solution exists");
                }
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearGrid();
            }
        });

        JButton loadButton = new JButton("Load Puzzle");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPuzzle();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(solveButton);
        controlPanel.add(clearButton);
        controlPanel.add(loadButton);

        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private boolean readGrid(int[][] grid) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String text = cells[i][j].getText();
                if (text.isEmpty()) {
                    grid[i][j] = 0;
                } else {
                    try {
                        int num = Integer.parseInt(text);
                        if (num < 1 || num > 9) {
                            JOptionPane.showMessageDialog(null, "Invalid input at (" + (i + 1) + "," + (j + 1) + "): must be a number between 1 and 9.");
                            return false;
                        }
                        grid[i][j] = num;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input at (" + (i + 1) + "," + (j + 1) + "): must be a number.");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void displayGrid(int[][] grid) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j].setText(Integer.toString(grid[i][j]));
            }
        }
    }

    private void clearGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j].setText("");
            }
        }
    }

    private void loadPuzzle() {
        int[][] puzzle = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (puzzle[i][j] != 0) {
                    cells[i][j].setText(Integer.toString(puzzle[i][j]));
                } else {
                    cells[i][j].setText("");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuSolver().setVisible(true);
            }
        });
    }
}
