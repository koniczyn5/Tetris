package com.controller;

import com.model.DropBoard;
import com.model.MainBoard;
import com.model.PunishmentsLogic;
import com.model.Score;
import com.view.*;

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

    private Board_view_interface mainBoardView;
    private MainBoard mainBoard;

    private Board_view_interface dropBoardView;
    private DropBoard dropBoard;

    private Timer_look timerLook;
    private Info_Panel_look infoPanelLook;
    private Score score;

    private PunishmentsLogic punishmentsLogic;
    private final int PUNISHMENTS_DURATION=10000;

    public Main_controller (Game parent) {
        super();
        Main_Board_look boardLook=new Main_Board_look(BOARD_WIDTH,BOARD_HEIGHT);
        mainBoardView =boardLook;
        mainBoard=boardLook.getMainBoard();
        boardLook.setSize(200,440);
        boardLook.setLocation(0,90);
        boardLook.addKeyListener(this);

        Drop_Board_look dropBoardLook=new Drop_Board_look(BOARD_WIDTH);
        dropBoardView =dropBoardLook;
        dropBoard=dropBoardLook.getDropBoard();
        dropBoardLook.setSize(200,80);
        dropBoardLook.setLocation(0,0);
        dropBoardLook.addKeyListener(this);

        timerLook=new Timer_look();
        timerLook.setSize(200,80);
        timerLook.setLocation(210, 0);

        punishmentsLogic =new PunishmentsLogic(PUNISHMENTS_DURATION);

        infoPanelLook=new Info_Panel_look();
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
        punishmentsLogic.updateTimes(BOARD_PERIOD_INTERVAL);
        if(mainBoard.isGameOver()) {gameOver(); return;}
        if (isPaused || dropBoard.isDropping()) return;
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

    Board_view_interface getMainBoardView() { return mainBoardView; }

    Board_view_interface getDropBoardView() { return dropBoardView; }

    MainBoard getMainBoard() { return mainBoard; }

    DropBoard getDropBoard() { return dropBoard; }

    PunishmentsLogic getPunishmentsLogic() { return punishmentsLogic; }

    Timer_look getTimerLook() { return timerLook; }

    boolean isStarted() { return isStarted; }

    boolean isPaused() { return isPaused; }
}
