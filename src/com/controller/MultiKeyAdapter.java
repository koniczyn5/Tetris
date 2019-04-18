package com.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MultiKeyAdapter extends KeyAdapter {


    //'escape'-0, 'space'-1, 'q'-2, 'e'-3, 'a'-4, 's'-5, 'd'-6
    boolean [] Keys;
    private final int KeysNumber = 7;
    MultiKeyAdapter()
    {
        Keys=new boolean[KeysNumber];
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        int keycode = e.getKeyCode();
        if (keycode== KeyEvent.VK_ESCAPE)
        {
            Keys[0]=true;
        }
        if (keycode== KeyEvent.VK_SPACE)
        {
            Keys[1]=true;
        }
        if (keycode== KeyEvent.VK_Q)
        {
            Keys[2]=true;
        }
        if (keycode== KeyEvent.VK_E)
        {
            Keys[3]=true;
        }
        if (keycode== KeyEvent.VK_A)
        {
            Keys[4]=true;
        }
        if (keycode== KeyEvent.VK_S)
        {
            Keys[5]=true;
        }
        if (keycode== KeyEvent.VK_D)
        {
            Keys[6]=true;
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e)
    {
        System.out.println("key released");
        int keycode = e.getKeyCode();
        if (keycode== KeyEvent.VK_ESCAPE)
        {
            Keys[0]=false;
        }
        if (keycode== KeyEvent.VK_SPACE)
        {
            Keys[1]=false;
        }
        if (keycode== KeyEvent.VK_Q)
        {
            Keys[2]=false;
        }
        if (keycode== KeyEvent.VK_E)
        {
            Keys[3]=false;
        }
        if (keycode== KeyEvent.VK_A)
        {
            Keys[4]=false;
        }
        if (keycode== KeyEvent.VK_S)
        {
            Keys[5]=false;
        }
        if (keycode== KeyEvent.VK_D)
        {
            Keys[6]=false;
        }
    }
}
