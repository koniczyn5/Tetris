package com.controller;

import java.util.Timer;

class CountdownTimer extends Timer {

    private CountdownTimerTask task;

    CountdownTimer(Main_controller controller, int time,int minTime, int interval) {
        super(true);
        task=new CountdownTimerTask(controller, time,minTime, interval);
        scheduleAtFixedRate(task,0,interval);
    }

    void startTimer()
    {
        task.reset();
        task.setActive();
    }

    int stopTimer()
    {
        task.deActivate();
        task.incrementBlocksDropped();
        return task.getTimerTime();
    }
}
