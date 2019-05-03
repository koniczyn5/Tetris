package com.view;

import javax.swing.*;
import java.awt.*;

public class Timer_look extends JPanel {

    private JLabel timerText;

    public Timer_look(){
        setFocusable(false);
        timerText=new JLabel("",SwingConstants.CENTER);
        timerText.setFont(new Font("Sherif", Font.PLAIN,80));
        JLabel Headline=new JLabel("Time left: ",SwingConstants.CENTER);
        setLayout(new BorderLayout());
        add(Headline, BorderLayout.NORTH);
        add(timerText, BorderLayout.CENTER);
    }

    public void SetTime(Integer timeLeft) {
        String text = timeLeft / 1000 + ".";
        if((timeLeft/10)%100<10) text += "0";
        text += (timeLeft / 10) % 100;
        timerText.setText(text);
    }

}
