package com.view;

import javax.swing.*;
import java.awt.*;

public class Boarder extends JPanel {

    public Boarder(){
        initBoarders();
    }

    private void initBoarders(){

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.fillRect(0,0,215,10);
    }
}
