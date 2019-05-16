package com.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TreeSet;

public class MultiKeyAdapter extends KeyAdapter {

    private TreeSet<Integer> Keys;
    private TreeSet<Integer> AlreadyPressedKeys;
    MultiKeyAdapter() {
        AlreadyPressedKeys =new TreeSet<>();
        Keys= new TreeSet<>();
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        int keyCode = e.getKeyCode();
        Keys.add(keyCode);
        e.consume();
    }

    @Override
    public synchronized void keyReleased(KeyEvent e)
    {
        System.out.println("key released");
        int keyCode = e.getKeyCode();
        Keys.remove(keyCode);
        AlreadyPressedKeys.remove(keyCode);
        e.consume();
    }

    boolean isInKeys(Integer key)
    {
        return Keys.contains(key);
    }

    boolean isNotInAlreadyPressedKeys(Integer key)
    {
        return !AlreadyPressedKeys.contains(key);
    }

    void addSingleKey(Integer key) { AlreadyPressedKeys.add(key);}
}
