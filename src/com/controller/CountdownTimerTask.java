package com.controller;

import com.model.PunishmentsLogic;

import java.util.TimerTask;

public class CountdownTimerTask extends TimerTask {
    private Main_controller mainController;
    private final int BASE_TIMER_TIME;
    private final int MIN_TIMER_TIME;
    private int timerTime;
    private int accurancy;
    private boolean isActive;
    private int blocksDropped;

    CountdownTimerTask(Main_controller controller,int startingTimerTime,int minTimerTime, int accurancy)
    {
        BASE_TIMER_TIME=startingTimerTime;
        MIN_TIMER_TIME=minTimerTime;
        this.accurancy=accurancy;
        mainController=controller;
        start();
    }

    void start()
    {
        timerTime=BASE_TIMER_TIME;
        isActive=false;
        blocksDropped=1;
    }
    @Override
    public void run()
    {
        if(mainController.isPaused() || !isActive) return;
        timerTime-=accurancy;
        if(timerTime<=0)
        {
            mainController.DropToMainBoard();
            mainController.cancelCountdownTimer();
            mainController.getPunishmentsLogic().punish();
            mainController.getTimerLook().SetTime(0);
        }
        else
        {
            mainController.getTimerLook().SetTime(timerTime);
            mainController.getDropBoardView().repaint();
        }
    }

    void reset()
    {
        timerTime= (int) Math.min(BASE_TIMER_TIME,MIN_TIMER_TIME+BASE_TIMER_TIME/Math.log(blocksDropped));
        if(mainController.getPunishmentsLogic().checkStatus(PunishmentsLogic.PunishmentTypes.fasterTimer))
            timerTime=timerTime/2;
    }

    int getTimerTime() { return timerTime; }

    void setActive() { isActive=true; }

    void deActivate() {isActive=false; }

    void incrementBlocksDropped()
    {
        ++blocksDropped;
    }
}
