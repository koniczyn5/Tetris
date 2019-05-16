package com.controller;

import com.view.*;

import java.util.Timer;

public class Main_controller extends MultiKeyAdapter {

    private Timer keysTimer;
    private final int KEYS_INITIAL_DELAY = 100;
    private final int KEYS_PERIOD_INTERVAL = 120;

    private Timer boardTimer;
    private final int BOARD_INITIAL_DELAY = 100;
    private final int BOARD_PERIOD_INTERVAL = 300;

    private Timer countdownTimer;
    public final int COUNTDOWN_TIME =2000;
    public final int COUNTDOWN_PERIOD_INTERVAL =10;

    private boolean isStarted = false;
    private boolean isPaused = false;

    private Board_view_interface bvi;
    private Drop_Board_view_interface dbvi;
    private Timer_look timerLook;
    private Info_Panel_look infoPanelLook;

    public Main_controller (Game parent, int board_width, int board_height) {
        super();
        Main_Board_look boardLook=new Main_Board_look(board_width,board_height);
        bvi=boardLook;
        boardLook.setSize(200,440);
        boardLook.setLocation(0,90);
        boardLook.addKeyListener(this);
        Drop_Board_look dropBoardLook=new Drop_Board_look(board_width);
        dbvi=dropBoardLook;
        dropBoardLook.setSize(200,80);
        dropBoardLook.setLocation(0,0);
        dropBoardLook.addKeyListener(this);
        timerLook=new Timer_look();
        timerLook.setSize(200,80);
        timerLook.setLocation(210, 0);
        infoPanelLook=new Info_Panel_look();
        infoPanelLook.setSize(200, 440);
        infoPanelLook.setLocation(210,90);
        parent.add(dropBoardLook);
        parent.add(boardLook);
        parent.add(timerLook);
        parent.add(infoPanelLook);
        initMain();
    }

    private void initMain() {
        keysTimer=new Timer(true);
        keysTimer.scheduleAtFixedRate(new KeysTask(this),KEYS_INITIAL_DELAY,KEYS_PERIOD_INTERVAL);
    }

    public void start() {
        isStarted=true;
        infoPanelLook.setStatusBar("Playing...");
        bvi.getMainBoard().start();
        bvi.repaint();
        startBoardTimer(BOARD_INITIAL_DELAY,BOARD_PERIOD_INTERVAL);
        dbvi.getDropBoard().start();
        dbvi.repaint();
        SpawnNewPiece();
    }

    void pause() {
        if (!isStarted) {
            return;
        }
        isPaused=!isPaused;
        if(isPaused)
        {
            infoPanelLook.setStatusBar("Game is paused.");
        }
        else
        {
            infoPanelLook.setStatusBar("Playing...");
        }
    }

    public void SpawnNewPiece()
    {
        dbvi.getDropBoard().newPiece();
        startCountdownTimer(COUNTDOWN_TIME,COUNTDOWN_PERIOD_INTERVAL);
        dbvi.repaint();
    }

    public void DropToMainBoard()
    {
        bvi.getMainBoard().newPiece(dbvi.getDropBoard().getCurPiece(),dbvi.getDropBoard().getCurX());
        dbvi.getDropBoard().dropDown();
        dbvi.repaint();
        bvi.repaint();
    }

    private void startBoardTimer(int delay, int periodInterval) {
        boardTimer=new Timer(true);
        boardTimer.scheduleAtFixedRate(new BoardTask(this), delay, periodInterval);
    }

    public void cancelBoardTimer() { boardTimer.cancel(); }

    public void startCountdownTimer(int time, int periodInterval) {
        if(bvi.getMainBoard().isFalling()){return;}
        countdownTimer=new Timer(true);
        countdownTimer.scheduleAtFixedRate(new CountdownTimerTask(this, time, periodInterval),0, periodInterval);
    }

    public void doGameCycle()
    {
        if(bvi.getMainBoard().isGameOver()) {gameOver(); return;}
        if (isPaused || dbvi.getDropBoard().isDropping()) return;
        if (!bvi.getMainBoard().isFalling() && !dbvi.getDropBoard().isDropping()) SpawnNewPiece();
        else bvi.getMainBoard().oneLineDown();
        bvi.repaint();
    }

    public void gameOver()
    {
        infoPanelLook.setStatusBar("Game over. Press R to restart.");
        cancelBoardTimer();
        isStarted=false;
    }

    void cancelCountdownTimer() { countdownTimer.cancel(); }

    public Board_view_interface getBvi() { return bvi; }

    public Drop_Board_view_interface getDbvi() { return dbvi; }

    Timer_look getTimerLook() { return timerLook; }

    boolean isStarted() { return isStarted; }

    public boolean isPaused() { return isPaused; }
}
