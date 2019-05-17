package com.controller;

import java.util.Timer;

public class CountdownTimer extends Timer {

    private CountdownTimerTask task;
    private int timerTime;
    private int accurancy;

    public CountdownTimer(Main_controller controller, int time, int interval) {
        super(true);
        timerTime=time;
        accurancy=interval;
        task=new CountdownTimerTask(controller, time, interval);
    }

    public void startTimer()
    {
        task.reset(timerTime,accurancy);
        scheduleAtFixedRate(task,0,accurancy);
    }

    public int stopTimer()
    {
        cancel();
        purge();
        return task.getTimerTime();
    }
}
