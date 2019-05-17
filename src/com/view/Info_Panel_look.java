package com.view;

import com.model.Score;

import javax.swing.*;
import java.awt.*;

public class Info_Panel_look extends JPanel {

    private JLabel statusBar;
    private Score_look scoreBar;

    public Info_Panel_look()
    {
        statusBar=new JLabel("",SwingConstants.CENTER);
        scoreBar=new Score_look();
        setLayout(new BorderLayout());
        add(scoreBar, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
    }

    public void setStatusBar(String text) {
        statusBar.setText(text);
    }
    public void displayScore() { scoreBar.displayScore(); }
    public Score getScoreModel() { return scoreBar.getScoreModel(); }
}
