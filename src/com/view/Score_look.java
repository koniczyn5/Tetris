package com.view;

import com.model.Score;

import javax.swing.*;
import java.awt.*;

class Score_look extends JLabel {

    private Score score = new Score();

    Score_look()
    {
        super("",SwingConstants.CENTER);
        setFont(new Font("Sherif",Font.PLAIN,30));
    }

    void displayScore()
    {
        String text="Score: "+ score.getScore();
        setText(text);
    }

    Score getScoreModel() { return score; }
}
