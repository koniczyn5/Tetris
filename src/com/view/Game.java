package com.view;

import javax.swing.*;
import java.awt.*;

//TODO: Edit main controller
//TODO: Reverse order of components
//TODO: Write Dropping board
//TODO: Write Info panel, Score logic, Score controller
//TODO: Write punishments and powers
//TODO: Write Unit tests
//TODO: Write Main menu
//TODO: Rework look

public class Game extends JFrame {

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 22;

    private JLabel statusbar;

    public Game() {

        initUI();
    }

    private void initUI() {

        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);

        Board_look board = new Board_look(this, BOARD_WIDTH, BOARD_HEIGHT);
        add(board);
        board.getBoard().start();

        setTitle("Game");
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public JLabel getStatusBar() {

        return statusbar;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Game game = new Game();
            game.setVisible(true);
        });
    }
}
