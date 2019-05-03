package com.view;

import javax.swing.*;
import java.awt.*;

public class Info_Panel_look extends JPanel {

    private JLabel statusBar;
    private JLabel scoreBar;

    public Info_Panel_look()
    {
        statusBar=new JLabel("",SwingConstants.CENTER);
        scoreBar=new JLabel("",SwingConstants.CENTER);
        scoreBar.setFont(new Font("Sherif",Font.PLAIN,30));
        setLayout(new BorderLayout());
        add(scoreBar, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
    }

    public void setStatusBar(String text) {
        statusBar.setText(text);
    }
    public void setScoreBar(int score) {
        String text="Score: "+ score;
        scoreBar.setText(text);
    }
}
