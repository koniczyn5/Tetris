package com.controller;

import com.model.Board;
import com.model.Shape;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TreeSet;

//TODO: Fix the bug with sequence: Press A, Press D, Release D
public class MultiKeyAdapter extends KeyAdapter {

    private TreeSet<Integer> Keys;
    private Board board;
    MultiKeyAdapter(Board board)
    {
        this.board=board;
        Keys= new TreeSet<>();
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        int keyCode = e.getKeyCode();
        Keys.add(keyCode);
        doKeysActions();
    }

    @Override
    public synchronized void keyReleased(KeyEvent e)
    {
        System.out.println("key released");
        int keyCode = e.getKeyCode();
        Keys.remove(keyCode);
    }

    private void doKeysActions()
    {
        if(Keys.isEmpty() || board.isPaused() || board.getCurPiece().getShape() == Shape.Tetrominoe.NoShape){return;}
        if (!board.isStarted()) {
            if(Keys.contains(KeyEvent.VK_R))
            {
                board.start();
            }
            return;
        }

        if(Keys.contains(KeyEvent.VK_ESCAPE))
        {
            board.pause();
            return;
        }

        if(Keys.contains(KeyEvent.VK_A) && !Keys.contains(KeyEvent.VK_D))
        {
            board.tryMove(board.getCurPiece(), board.getCurX() - 1, board.getCurY());
        }
        if(Keys.contains(KeyEvent.VK_D) && !Keys.contains(KeyEvent.VK_A))
        {
            board.tryMove(board.getCurPiece(), board.getCurX() + 1, board.getCurY());
        }
        if(Keys.contains(KeyEvent.VK_Q) && !Keys.contains(KeyEvent.VK_E))
        {
            board.tryMove(board.getCurPiece().rotateLeft(), board.getCurX(), board.getCurY());
        }
        if(Keys.contains(KeyEvent.VK_E) && !Keys.contains(KeyEvent.VK_Q))
        {
            board.tryMove(board.getCurPiece().rotateRight(), board.getCurX(), board.getCurY());
        }
        if(Keys.contains(KeyEvent.VK_S))
        {
            board.oneLineDown();
        }
        if(Keys.contains(KeyEvent.VK_SPACE))
        {
            board.dropDown();
        }
    }
}
