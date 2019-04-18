package com.controller;

import com.model.Board;
import com.model.Shape;

import java.util.Timer;
import java.util.TimerTask;

public class Main_controller extends MultiKeyAdapter { //TODO: zaimplementuj obsluge kilku wcisnietych klawiszy:
    // https://stackoverflow.com/questions/2623995/swings-keylistener-and-multiple-keys-pressed-at-the-same-time

    private Board board;
    private final int KEYS_INITIAL_DELAY = 100;
    private final int KEYS_PERIOD_INTERVAL = 100;

    private Timer keysTimer;

    public Main_controller (Board board)
    {
        super();
        this.board=board;
        keysTimer=new Timer();
        keysTimer.scheduleAtFixedRate(new KeysActions(), KEYS_INITIAL_DELAY, KEYS_PERIOD_INTERVAL);
    }

    private void doKeysActions()
    {

        if (!board.isStarted()) {
            if(Keys[1])
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

    private class KeysActions extends TimerTask
    {
        @Override
        public void run() {
            doKeysActions();
        }
    }

}
