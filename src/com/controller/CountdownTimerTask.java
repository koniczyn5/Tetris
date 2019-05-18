package com.controller;

import java.util.TimerTask;

public class CountdownTimerTask extends TimerTask {
    private Main_controller mainController;
    private int timerTime;
    private int accurancy;
    private boolean isActive;

    CountdownTimerTask(Main_controller controller,int timerTime, int accurancy)
    {
        mainController=controller;
        reset(timerTime,accurancy);
        isActive=false;
    }
    @Override
    public void run()
    {
        if(mainController.isPaused() || !isActive) return;
        timerTime-=accurancy;
        if(timerTime<=0)
        {
            //zrzuc klocek i naloz kare
            mainController.DropToMainBoard();
            mainController.cancelCountdownTimer();
            mainController.getTimerLook().SetTime(0);
        }
        else
        {
            mainController.getTimerLook().SetTime(timerTime);
            mainController.getDropBoardView().repaint();
        }
    }

    void reset(int timerTime, int accurancy)
    {
        this.timerTime=timerTime;
        this.accurancy=accurancy;
    }

    int getTimerTime() { return timerTime; }

    void setActive() { isActive=true; }

    void deActivate() {isActive=false; }
}
