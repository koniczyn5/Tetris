package com.controller;

import java.util.TimerTask;

public class BoardTask extends TimerTask {

    private Main_controller mainController;

    BoardTask(Main_controller controller){
        mainController=controller;
    }

    @Override
    public void run() {

        mainController.doGameCycle();
    }
}
