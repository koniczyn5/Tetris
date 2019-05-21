package com.view;

import javax.swing.*;
import java.awt.*;

public class Superpowers_look extends JPanel {
    private JLabel textBar;

    Superpowers_look(ImageIcon image, String Title){
        setLayout(new BorderLayout(5,5));
        textBar=new JLabel("",SwingConstants.CENTER);
        textBar.setFont(new Font("", Font.PLAIN,9));
        JLabel titleBar = new JLabel();
        titleBar.setFont(new Font("",Font.BOLD,11));
        titleBar.setText(Title);
        JLabel picture=new JLabel(image);
        add(titleBar, BorderLayout.NORTH);
        add(textBar,BorderLayout.SOUTH);
        add(picture, BorderLayout.CENTER);
    }

    public void updateBar(int time, int cost) {
        if(time>0) {
            String text = "TimeLeft: " + time / 1000 + ".";
            if ((time / 10) % 100 < 10) text += "0";
            text += (time / 10) % 100;
            textBar.setText(text);
        }
        else textBar.setText("Price: "+cost);
    }
}
