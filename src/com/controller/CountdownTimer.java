package com.controller;

import java.util.Timer;

class CountdownTimer extends Timer {

    private CountdownTimerTask task;
    private int timerTime;
    private int accurancy;

    CountdownTimer(Main_controller controller, int time, int interval) {
        super(true);
        timerTime=time;
        accurancy=interval;
        task=new CountdownTimerTask(controller, time, interval);
        scheduleAtFixedRate(task,0,accurancy);
    }

    void startTimer()
    {
        task.reset(timerTime,accurancy);
        task.setActive();
    }

    int stopTimer()
    {
        task.deActivate();
        return task.getTimerTime();
    }
}
