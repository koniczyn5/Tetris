package com.view;

import com.model.PunishmentsLogic;
import com.model.Score;

import javax.swing.*;
import java.awt.*;

public class Info_Panel_look extends JPanel {

    private JLabel statusBar;
    private Score_look scoreBar;
    private Punishment_look grid;
    private PunishmentsLogic punishmentsLogic;

    public Info_Panel_look(PunishmentsLogic punishmentsLogic)
    {
        this.punishmentsLogic=punishmentsLogic;
        statusBar=new JLabel("",SwingConstants.CENTER);
        scoreBar=new Score_look();
        grid=new Punishment_look(punishmentsLogic.getImage(PunishmentsLogic.PunishmentTypes.fasterTimer));
        setLayout(new BorderLayout());
        add(scoreBar, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
        add(grid,BorderLayout.CENTER);
    }

    public void setStatusBar(String text) {
        statusBar.setText(text);
    }
    public void displayScore() { scoreBar.displayScore(); }
    public void updateGrid() {grid.updateBar(punishmentsLogic.getTimeLeft(PunishmentsLogic.PunishmentTypes.fasterTimer));}
    public Score getScoreModel() { return scoreBar.getScoreModel(); }
}
