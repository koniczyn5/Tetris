package com.controller;

import java.util.TimerTask;

public class CountdownTimerTask extends TimerTask {
    private Main_controller mainController;
    private int timerTime;
    private int accurancy;

    CountdownTimerTask(Main_controller controller,int timerTime, int accurancy)
    {
        mainController=controller;
        this.timerTime=timerTime;
        this.accurancy=accurancy;
    }
    @Override
    public void run()
    {
        timerTime-=accurancy;
        if(timerTime<=0)
        {
            //zrzuc klocek i naloz kare
            mainController.getDbvi().getDropBoard().dropDown();
            mainController.cancelCountdownTimer();
        }
        else
        {
            //zaktualizuj wyswietlacz czasu
        }
    }
}
