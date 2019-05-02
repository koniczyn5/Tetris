package com.view;

import javax.swing.*;
import java.awt.*;

public class Timer_look extends JPanel {

    private JLabel timerText;

    public Timer_look(){
        setFocusable(false);
        timerText=new JLabel();
        timerText.setFont(new Font("Sherif", Font.PLAIN,60));
        add(timerText, BorderLayout.CENTER);
        setSize(200,80);
    }

    public void SetTime(Integer timeLeft) {
        String text = timeLeft / 1000 + "." + (timeLeft/10) % 100;
        timerText.setText(text);
    }

}
