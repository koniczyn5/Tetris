package com.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TreeSet;

public class MultiKeyAdapter extends KeyAdapter {

    private TreeSet<Integer> Keys;
    MultiKeyAdapter()
    {
        Keys= new TreeSet<>();
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        int keyCode = e.getKeyCode();
        Keys.add(keyCode);
    }

    @Override
    public synchronized void keyReleased(KeyEvent e)
    {
        System.out.println("key released");
        int keyCode = e.getKeyCode();
        Keys.remove(keyCode);
    }

    boolean isInKeys(Integer key)
    {
        return Keys.contains(key);
    }
}
