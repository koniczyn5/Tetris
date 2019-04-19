package com.controller;

import com.model.Board;
import com.model.Shape;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MultiKeyAdapter extends KeyAdapter {

    private boolean[] Keys;
    private Board board;
    MultiKeyAdapter(Board board)
    {
        this.board=board;
        Keys= new boolean[KeyEvent.VK_Z];
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        int keyCode = e.getKeyCode();
        Keys[keyCode]=true;
        doKeysActions();
    }

    @Override
    public synchronized void keyReleased(KeyEvent e)
    {
        System.out.println("key released");
        int keyCode = e.getKeyCode();
        Keys[keyCode]=false;
    }

    private void doKeysActions()
    {
        if(board.isPaused() || board.getCurPiece().getShape() == Shape.Tetrominoe.NoShape){return;}
        if (!board.isStarted()) {
            if(Keys[KeyEvent.VK_R])
            {
                board.start();
            }
            return;
        }

        if(Keys[KeyEvent.VK_ESCAPE])
        {
            board.pause();
            return;
        }

        if(Keys[KeyEvent.VK_A] && !Keys[KeyEvent.VK_D])
        {
            board.tryMove(board.getCurPiece(), board.getCurX() - 1, board.getCurY());
        }
        if(Keys[KeyEvent.VK_D] && !Keys[KeyEvent.VK_A])
        {
            board.tryMove(board.getCurPiece(), board.getCurX() + 1, board.getCurY());
        }
        if(Keys[KeyEvent.VK_Q] && !Keys[KeyEvent.VK_E])
        {
            board.tryMove(board.getCurPiece().rotateLeft(), board.getCurX(), board.getCurY());
        }
        if(Keys[KeyEvent.VK_E] && !Keys[KeyEvent.VK_Q])
        {
            board.tryMove(board.getCurPiece().rotateRight(), board.getCurX(), board.getCurY());
        }
        if(Keys[KeyEvent.VK_S])
        {
            board.oneLineDown();
        }
        if(Keys[KeyEvent.VK_SPACE])
        {
            board.dropDown();
        }
    }
}
