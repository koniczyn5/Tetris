package com.view;

import com.controller.Main_controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

//TODO: Reverse order of components DONE
//TODO: Write Dropping board DONE
//TODO: Write Info panel, Score logic, Score controller DONE
//TODO: Write punishments and powers
//TODO: Write Unit tests
//TODO: Write overtime growth DONE
//TODO: Write Main menu
//TODO: Rework look

public class Game extends JFrame {

    private Game() {

        initUI();
    }

    private void initUI() {
        Main_controller controller = new Main_controller(this);
        controller.start();
        getContentPane().setBackground(Color.darkGray);
        setTitle("Tetris: redone");
        try {
            setIconImage(ImageIO.read(getClass().getResource("/images/logo.png")));
        }
        catch (IOException e) {
        System.exit(1);
        }
        setSize(425, 570);
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
