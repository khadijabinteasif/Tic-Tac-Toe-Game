/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tictactoe;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons; // Array to store the buttons for the Tic Tac Toe grid
    private JLabel statusLabel; // Label to display game status
    private String[] board; // Array to represent the Tic Tac Toe board state
    private String turn; // Represents whose turn it is (X or O)
    private boolean gameOver; // Flag to indicate if the game is over

    public TicTacToe() {
        setTitle("Tic Tac Toe"); // Set the title of the window
        setSize(300, 350); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Specify what happens when the window is closed
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel(); // Create a panel to hold the buttons
        panel.setLayout(new GridLayout(3, 3)); // Set the layout of the panel to a 3x3 grid

        buttons = new JButton[9]; // Initialize the array of buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(); // Create a new button
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40)); // Set the font of the button text
            buttons[i].setBackground(Color.BLACK); // Set the background color of the button to black
            buttons[i].setForeground(new Color(173, 216, 230)); // Set the text color of the button to ice blue
            buttons[i].addActionListener(this); // Add an ActionListener to the button
            panel.add(buttons[i]); // Add the button to the panel
        }

        statusLabel = new JLabel("X's Turn", SwingConstants.CENTER); // Create a label to display game status
        statusLabel.setForeground(Color.BLACK); // Set the text color of the label to black
        add(statusLabel, BorderLayout.NORTH); // Add the label to the top of the window
        add(panel, BorderLayout.CENTER); // Add the panel to the center of the window

        setVisible(true); // Make the window visible

        initializeGame(); // Initialize the game state
    }

    private void initializeGame() {
        board = new String[9]; // Initialize the array to represent the Tic Tac Toe board
        turn = "X"; // Set the initial player turn to X
        gameOver = false; // Set the initial game over flag to false

        for (int i = 0; i < 9; i++) {
            board[i] = ""; // Initialize each cell of the board to empty
            buttons[i].setText(board[i]); // Set the text of the corresponding button to empty
        }

        statusLabel.setText("X's Turn"); // Set the status label text to indicate whose turn it is
    }

    public void actionPerformed(ActionEvent e) {
        if (gameOver)
            return;

        JButton button = (JButton) e.getSource(); // Get the button that was clicked
        int index = Arrays.asList(buttons).indexOf(button); // Get the index of the clicked button
        if (!board[index].equals(""))
            return;

        board[index] = turn; // Set the corresponding cell of the board to the current player's symbol
        button.setText(turn); // Set the text of the clicked button to the current player's symbol

        if (checkWinner()) {
            statusLabel.setText(turn + " wins!"); // Update the status label to indicate the winner
            gameOver = true; // Set the game over flag to true
        } else if (checkDraw()) {
            statusLabel.setText("It's a draw!"); // Update the status label to indicate a draw
            gameOver = true; // Set the game over flag to true
        } else {
            turn = (turn.equals("X")) ? "O" : "X"; // Switch turns between X and O
            statusLabel.setText(turn + "'s Turn"); // Update the status label to indicate whose turn it is
        }
    }

    private boolean checkWinner() {
        for (int i = 0; i < 8; i++) {
            String line = null;
            switch (i) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
            if (line.equals("XXX") || line.equals("OOO")) { // Check if any player has won
                return true; // Return true if a player has won
            }
        }
        return false; // Return false if no player has won
    }

    private boolean checkDraw() {
        for (String cell : board) {
            if (cell.equals("")) {
                return false; // Return false if there are still empty cells on the board
            }
        }
        return true; // Return true if all cells on the board are filled
    }

    public static void main(String[] args) {
        new TicTacToe(); // Create an instance of the TicTacToe class to start the game
    }
}



























