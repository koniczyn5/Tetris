package com.controller;

import com.model.Board;
import com.model.Shape;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MultiKeyAdapter extends KeyAdapter {


    //'escape'-0, 'space'-1, 'q'-2, 'e'-3, 'a'-4, 's'-5, 'd'-6, 'r'-7
    private boolean [] Keys;
    private final int KeysNumber = 8;
    private Board board;
    MultiKeyAdapter(Board board)
    {
        this.board=board;
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
        if (keycode== KeyEvent.VK_R)
        {
            Keys[7]=true;
        }
        doKeysActions();
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
        if (keycode== KeyEvent.VK_R)
        {
            Keys[7]=false;
        }
    }

    private void doKeysActions()
    {

        if (!board.isStarted()) {
            if(Keys[7])
            {
                board.start();
            }
            return;
        }

        if(Keys[0])
        {
            board.pause();
            return;
        }
        if(board.isPaused() || board.getCurPiece().getShape() == Shape.Tetrominoe.NoShape) {return;}
        if(Keys[4] && !Keys[6])
        {
            board.tryMove(board.getCurPiece(), board.getCurX() - 1, board.getCurY());
        }
        if(Keys[6] && !Keys[4])
        {
            board.tryMove(board.getCurPiece(), board.getCurX() + 1, board.getCurY());
        }
        if(Keys[2] && !Keys[3])
        {
            board.tryMove(board.getCurPiece().rotateLeft(), board.getCurX(), board.getCurY());
        }
        if(Keys[3] && !Keys[2])
        {
            board.tryMove(board.getCurPiece().rotateRight(), board.getCurX(), board.getCurY());
        }
        if(Keys[5])
        {
            board.oneLineDown();
        }
        if(Keys[1])
        {
            board.dropDown();
        }
    }
}
