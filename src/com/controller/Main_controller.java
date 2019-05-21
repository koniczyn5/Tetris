package com.controller;

import com.model.*;
import com.view.Board_look;
import com.view.Game;
import com.view.Info_Panel_look;
import com.view.Timer_look;

import java.util.Timer;

public class Main_controller extends MultiKeyAdapter {

    private final int KEYS_INITIAL_DELAY = 100;
    private final int KEYS_PERIOD_INTERVAL = 120;

    private Timer boardTimer;
    private final int BOARD_INITIAL_DELAY = 100;
    private final int BOARD_PERIOD_INTERVAL = 300;

    private CountdownTimer countdownTimer;
    private final int BASE_COUNTDOWN_TIME = 2000;
    private final int MIN_COUNTDOWN_TIMER = 1000;
    private final int COUNTDOWN_PERIOD_INTERVAL = 10;

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 22;

    private boolean isStarted = false;
    private boolean isPaused = false;

    private Board_look mainBoardView;
    private MainBoard mainBoard;

    private Board_look dropBoardView;
    private DropBoard dropBoard;

    private Timer_look timerLook;
    private Info_Panel_look infoPanelLook;
    private Score score;

    private PunishmentsLogic punishmentsLogic;
    private final int PUNISHMENTS_DURATION=10000;

    private SuperpowersLogic superpowersLogic;
    private final int SUPERPOWERS_DURATION=15000;

    public Main_controller (Game parent) {
        super();
        mainBoard = new MainBoard(BOARD_WIDTH,BOARD_HEIGHT);
        Board_look boardLook = new Board_look(mainBoard);
        mainBoardView = boardLook;
        boardLook.setSize(200,440);
        boardLook.setLocation(0,90);
        boardLook.addKeyListener(this);

        dropBoard = new DropBoard(BOARD_WIDTH);
        Board_look dropBoardLook = new Board_look(dropBoard);
        dropBoardView = dropBoardLook;
        dropBoardLook.setSize(200,80);
        dropBoardLook.setLocation(0,0);
        dropBoardLook.addKeyListener(this);

        timerLook = new Timer_look();
        timerLook.setSize(200,80);
        timerLook.setLocation(210, 0);

        punishmentsLogic = new PunishmentsLogic(PUNISHMENTS_DURATION);
        superpowersLogic = new SuperpowersLogic(SUPERPOWERS_DURATION);

        infoPanelLook = new Info_Panel_look(punishmentsLogic, superpowersLogic);
        score=infoPanelLook.getScoreModel();
        infoPanelLook.setSize(200, 440);
        infoPanelLook.setLocation(210,90);

        parent.add(dropBoardLook);
        parent.add(boardLook);
        parent.add(timerLook);
        parent.add(infoPanelLook);
        initMain();
    }

    private void initMain() {
        Timer keysTimer = new Timer(true);
        keysTimer.scheduleAtFixedRate(new KeysTask(this),KEYS_INITIAL_DELAY,KEYS_PERIOD_INTERVAL);
        countdownTimer=new CountdownTimer(this, BASE_COUNTDOWN_TIME,MIN_COUNTDOWN_TIMER, COUNTDOWN_PERIOD_INTERVAL);

    }

    public void start() {
        isStarted=true;
        infoPanelLook.setStatusBar("Playing...");
        score.start();
        punishmentsLogic.start();
        countdownTimer.start();
        infoPanelLook.displayScore();
        mainBoard.start();
        mainBoardView.repaint();
        startBoardTimer(BOARD_INITIAL_DELAY,BOARD_PERIOD_INTERVAL);
        dropBoard.start();
        dropBoardView.repaint();
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

    private void SpawnNewPiece()
    {
        dropBoard.newPiece();
        startCountdownTimer();
        dropBoardView.repaint();
    }

    void DropToMainBoard()
    {
        mainBoard.newPiece(dropBoard.getCurPiece(), dropBoard.getCurX());
        dropBoard.dropDown();
        dropBoardView.repaint();
        mainBoardView.repaint();
    }

    private void startBoardTimer(int delay, int periodInterval) {
        boardTimer=new Timer(true);
        boardTimer.scheduleAtFixedRate(new BoardTask(this), delay, periodInterval);
    }

    private void cancelBoardTimer() { boardTimer.cancel(); }

    void doGameCycle()
    {
        if(mainBoard.isGameOver()) {gameOver(); return;}
        if (isPaused) return;
        punishmentsLogic.updateTimes(BOARD_PERIOD_INTERVAL);
        superpowersLogic.updateTimes(BOARD_PERIOD_INTERVAL);
        infoPanelLook.updateGrid();
        if(dropBoard.isDropping()) return;
        if (!mainBoard.isFalling() && !dropBoard.isDropping())
        {
            if(mainBoard.getRowsDestroyed()!=0) {
                score.addPointsForRow(mainBoard.getRowsDestroyed());
                mainBoard.resetRowsDestroyed();
                infoPanelLook.displayScore();
            }
            SpawnNewPiece();
        }
        else mainBoard.oneLineDown();
        mainBoardView.repaint();
    }

    private void gameOver()
    {
        infoPanelLook.setStatusBar("Game over. Press R to restart.");
        cancelBoardTimer();
        isStarted=false;
    }

    private void startCountdownTimer() {
        if(mainBoard.isFalling()){return;}
        countdownTimer.startTimer();
    }

    void cancelCountdownTimer() {
        float time = countdownTimer.stopTimer();
        if(time>=0) {
            score.addPointsForTime(time / 1000);
            infoPanelLook.displayScore();
        }
    }

    Board_look getMainBoardView() { return mainBoardView; }

    Board_look getDropBoardView() { return dropBoardView; }

    MainBoard getMainBoard() { return mainBoard; }

    DropBoard getDropBoard() { return dropBoard; }

    PunishmentsLogic getPunishmentsLogic() { return punishmentsLogic; }

    SuperpowersLogic getSuperpowersLogic() { return superpowersLogic; }

    Timer_look getTimerLook() { return timerLook; }

    Score getScore() { return  score; }

    Info_Panel_look getInfoPanelLook() { return infoPanelLook; }

    boolean isStarted() { return isStarted; }

    boolean isPaused() { return isPaused; }
}
