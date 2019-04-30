package com.view;

import com.controller.Main_controller;

import javax.swing.*;
import java.awt.*;

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


    public Game() {

        initUI();
    }

    private void initUI() {
        Main_controller controller = new Main_controller(this, BOARD_WIDTH, BOARD_HEIGHT);
        controller.getBvi().getMainBoard().start();
        getContentPane().setBackground(Color.darkGray);
        setTitle("Game");
        setSize(215, 570);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Game game = new Game();
            game.setVisible(true);
        });
    }
}
