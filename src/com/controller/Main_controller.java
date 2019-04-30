package com.controller;

import com.model.MainBoard;
import com.model.Shape;
import com.view.*;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Main_controller extends MultiKeyAdapter {

    private final int KEYS_INITIAL_DELAY = 100;
    private final int KEYS_PERIOD_INTERVAL = 120;
    private final int BOARD_INITIAL_DELAY = 100;
    private final int BOARD_PERIOD_INTERVAL = 300;
    private boolean isFallingFinished = false;
    private boolean isStarted = false;
    private boolean isPaused = false;
    private Board_view_interface bvi;
    private MainBoard mainBoard;
    private Drop_board_look dropBoardLook;
    private Timer keysTimer;
    private Timer boardTimer;

    public Main_controller (Game parent, int board_width, int board_height)
    {
        super();
        Main_Board_look boardLook=new Main_Board_look(this,board_width,board_height);
        dropBoardLook=new Drop_board_look(boardLook.getMainBoard(),board_width);
        bvi=boardLook;
        boardLook.setSize(200,440);
        boardLook.setLocation(0,90);
        boardLook.addKeyListener(this);
        dropBoardLook.setSize(200,80);
        dropBoardLook.setLocation(0,0);
        dropBoardLook.addKeyListener(this);
        Boarder line= new Boarder();
        line.setSize(215,10);
        line.setLocation(0,80);
        parent.add(line);
        parent.add(dropBoardLook);
        parent.add(boardLook);
        initMain(bvi.getMainBoard());
    }

    private void initMain(MainBoard mainBoard)
    {
        this.mainBoard = mainBoard;
        keysTimer=new Timer(true);
        keysTimer.scheduleAtFixedRate(new KeysTask(),KEYS_INITIAL_DELAY,KEYS_PERIOD_INTERVAL);
    }


    private class KeysTask extends TimerTask {
        @Override
        public void run() {
            if (!isStarted) {
                if (isInKeys(KeyEvent.VK_R)) {
                    mainBoard.start();
                }
                return;
            }
            if (isPaused || mainBoard.getCurPiece().getShape() == Shape.Tetrominoe.NoShape) {
                return;
            }

            if (isInKeys(KeyEvent.VK_ESCAPE)) {
                mainBoard.pause();
                return;
            }

            if (isInKeys(KeyEvent.VK_A) && !isInKeys(KeyEvent.VK_D)) {
                mainBoard.tryMove(mainBoard.getCurPiece(), mainBoard.getCurX() - 1, mainBoard.getCurY());
            }
            if (isInKeys(KeyEvent.VK_D) && !isInKeys(KeyEvent.VK_A)) {
                mainBoard.tryMove(mainBoard.getCurPiece(), mainBoard.getCurX() + 1, mainBoard.getCurY());
            }
            if (isInKeys(KeyEvent.VK_Q) && !isInKeys(KeyEvent.VK_E)) {
                mainBoard.tryMove(mainBoard.getCurPiece().rotateLeft(), mainBoard.getCurX(), mainBoard.getCurY());
            }
            if (isInKeys(KeyEvent.VK_E) && !isInKeys(KeyEvent.VK_Q)) {
                mainBoard.tryMove(mainBoard.getCurPiece().rotateRight(), mainBoard.getCurX(), mainBoard.getCurY());
            }
            if (isInKeys(KeyEvent.VK_S)) {
                mainBoard.oneLineDown();
            }
            if (isInKeys(KeyEvent.VK_SPACE)) {
                mainBoard.dropDown();
            }
        }
    }

    public Board_view_interface getBvi() {
        return bvi;
    }

    public boolean isFallingFinished() {
        return isFallingFinished;
    }

    public void setFallingFinished(boolean fallingFinished) {
        isFallingFinished = fallingFinished;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}
