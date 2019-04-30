package com.controller;

import com.model.MainBoard;
import com.view.*;

import java.util.Timer;

public class Main_controller extends MultiKeyAdapter {

    private Timer keysTimer;
    private final int KEYS_INITIAL_DELAY = 100;
    private final int KEYS_PERIOD_INTERVAL = 120;

    private Timer boardTimer;
    public final int BOARD_INITIAL_DELAY = 100;
    public final int BOARD_PERIOD_INTERVAL = 300;

    private Timer countdownTimer;
    public final int COUNTDOWN_TIME =2000;
    public final int COUNTDOWN_PERIOD_INTERVAL =10;

    private boolean isFalling = false;
    private boolean isStarted = false;
    private boolean isPaused = false;
    private boolean isDropping = false;

    private Board_view_interface bvi;
//    private MainBoard mainBoard;
    private Drop_Board_view_interface dbvi;

    public Main_controller (Game parent, int board_width, int board_height) {
        super();
        Main_Board_look boardLook=new Main_Board_look(this,board_width,board_height);
        bvi=boardLook;
        boardLook.setSize(200,440);
        boardLook.setLocation(0,90);
        boardLook.addKeyListener(this);
        Drop_Board_look dropBoardLook=new Drop_Board_look(this,board_width);
        dbvi=dropBoardLook;
        dropBoardLook.setSize(200,80);
        dropBoardLook.setLocation(0,0);
        dropBoardLook.addKeyListener(this);
        parent.add(dropBoardLook);
        parent.add(boardLook);
        initMain(bvi.getMainBoard());
    }

    private void initMain(MainBoard mainBoard) {
        //this.mainBoard = mainBoard;
        keysTimer=new Timer(true);
        keysTimer.scheduleAtFixedRate(new KeysTask(this),KEYS_INITIAL_DELAY,KEYS_PERIOD_INTERVAL);
    }

    public void start() {
        bvi.getMainBoard().start();
        dbvi.getDropBoard().start();
    }

    public void SpawnNewPiece()
    {
        isDropping=true;
        dbvi.getDropBoard().newPiece();
    }

    public void startBoardTimer(int delay, int periodInterval) {
        boardTimer=new Timer(true);
        boardTimer.scheduleAtFixedRate(new BoardTask(this), delay, periodInterval);
    }

    public void cancelBoardTimer() { boardTimer.cancel(); }

    public void startCountdownTimer(int time, int periodInterval) {
        if(isFalling){return;}
        countdownTimer=new Timer(true);
        countdownTimer.scheduleAtFixedRate(new CountdownTimerTask(this, time, periodInterval),0, periodInterval);
    }

    public void cancelCountdownTimer() { countdownTimer.cancel(); }

    public Board_view_interface getBvi() { return bvi; }
    public Drop_Board_view_interface getDbvi() { return dbvi; }

    public boolean isFalling() { return isFalling; }

    public void setFalling(boolean falling) { isFalling = falling; }

    public boolean isStarted() { return isStarted; }

    public void setStarted(boolean started) { isStarted = started; }

    public boolean isPaused() { return isPaused; }

    public void setPaused(boolean paused) { isPaused = paused; }

    public boolean isDropping() { return isDropping; }

    public void setDropping(boolean dropping) { isDropping = dropping; }
}
