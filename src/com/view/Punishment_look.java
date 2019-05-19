package com.view;

import javax.swing.*;
import java.awt.*;

public class Punishment_look extends JPanel {

    private JLabel textBar;

    Punishment_look(ImageIcon image){
        setLayout(new BorderLayout(5,5));
        textBar=new JLabel("",SwingConstants.CENTER);
        textBar.setFont(new Font("", Font.PLAIN,9));
        JLabel picture=new JLabel(image);
        add(textBar,BorderLayout.SOUTH);
        add(picture, BorderLayout.CENTER);
    }

    public void updateBar(int time) {
        if(time>0) {
            String text = "TimeLeft: " + time / 1000 + ".";
            if ((time / 10) % 100 < 10) text += "0";
            text += (time / 10) % 100;
            textBar.setText(text);
        }
        else textBar.setText("Not active");
    }

}
