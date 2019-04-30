package com.controller;

import com.model.MainBoard;
import com.view.Board_view_interface;
import com.view.Drop_board_look;
import com.view.Game;
import com.view.Main_Board_look;

import java.util.Timer;

public class Main_controller extends MultiKeyAdapter {

    private final int KEYS_INITIAL_DELAY = 100;
    private final int KEYS_PERIOD_INTERVAL = 120;
    public final int BOARD_INITIAL_DELAY = 100;
    public final int BOARD_PERIOD_INTERVAL = 300;
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
        parent.add(dropBoardLook);
        parent.add(boardLook);
        initMain(bvi.getMainBoard());
    }

    private void initMain(MainBoard mainBoard)
    {
        this.mainBoard = mainBoard;
        keysTimer=new Timer(true);
        keysTimer.scheduleAtFixedRate(new KeysTask(this),KEYS_INITIAL_DELAY,KEYS_PERIOD_INTERVAL);
    }

    public void startBoardTimer(int delay, int periodInterval)
    {
        boardTimer=new Timer(true);
        boardTimer.scheduleAtFixedRate(new BoardTask(this), delay, periodInterval);
    }

    public void cancelBoardTimer()
    {
        boardTimer.cancel();
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
