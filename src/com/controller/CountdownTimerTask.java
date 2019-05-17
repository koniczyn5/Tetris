package com.controller;

import java.util.TimerTask;

public class CountdownTimerTask extends TimerTask {
    private Main_controller mainController;
    private int timerTime;
    private int accurancy;

    CountdownTimerTask(Main_controller controller,int timerTime, int accurancy)
    {
        mainController=controller;
        reset(timerTime,accurancy);
    }
    @Override
    public void run()
    {
        if(mainController.isPaused()) return;
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

    public void reset(int timerTime, int accurancy)
    {
        this.timerTime=timerTime;
        this.accurancy=accurancy;
    }

    public int getTimerTime() { return timerTime; }
}
