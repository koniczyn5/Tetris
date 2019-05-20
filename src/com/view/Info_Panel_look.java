package com.view;

import com.model.PunishmentsLogic;
import com.model.Score;
import com.model.SuperpowersLogic;

import javax.swing.*;
import java.awt.*;

public class Info_Panel_look extends JPanel {

    private JLabel statusBar;
    private Score_look scoreBar;
    private Grid_look grid;

    public Info_Panel_look(PunishmentsLogic punishmentsLogic, SuperpowersLogic superpowersLogic)
    {
        statusBar=new JLabel("",SwingConstants.CENTER);
        scoreBar=new Score_look();
        grid=new Grid_look(punishmentsLogic, superpowersLogic);
        setLayout(new BorderLayout());
        add(scoreBar, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
        add(grid,BorderLayout.CENTER);
    }

    public void setStatusBar(String text) {
        statusBar.setText(text);
    }
    public void displayScore() { scoreBar.displayScore(); }
    public void updateGrid() {grid.updateGrid();}
    public Score getScoreModel() { return scoreBar.getScoreModel(); }
}
